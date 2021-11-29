import { removeSessionStorage } from './value.js';

export { rejectAlert, gradeChangeAlert };

function rejectAlert() {
    alert('잘못된 로그인 정보입니다!');
    removeSessionStorage(['jwt-auth-token', 'user-info']);
    location.href = '/login';
}

function gradeChangeAlert() {
    alert('해당 유저의 등급 변경이 감지되었습니다! 페이지를 다시 로드합니다!');
    location.reload();
}