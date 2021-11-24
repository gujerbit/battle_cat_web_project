import { getAccountInfo } from '../admin/admin.js';

export { writing };

async function writing(title, content, type, axios) {
  let { data } = await axios.post('/board_writing', {
    email: getAccountInfo().email,
    name: getAccountInfo().name,
    title: title,
    content: content,
    writing_date: new Date(),
    type: type,
  });

  // if(data > 0) {
  //   alert('글 작성 완료');
  //   location.href = '/board';
  // } else alert('글 작성 실패');
  console.log(data);
}