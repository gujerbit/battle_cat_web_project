import { getAccountInfo } from '../admin/admin.js';

export { writing };

async function writing(title, content, axios) {
  let { data } = await axios.post('/board_writing', {
    name: getAccountInfo().name,
    title: title,
    content: content,
    writing_date: new Date(),
  });

  if(data > 0) {
    alert('글 작성 완료');
    location.href = '/board';
  } else alert('글 작성 실패');
}