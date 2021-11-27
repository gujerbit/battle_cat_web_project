import { removeSessionStorage } from './value.js';

export { rejectAlert };

function rejectAlert() {
    alert('잘못된 로그인 정보입니다!');
    removeSessionStorage(['jwt-auth-token', 'user-info']);
    location.href = '/login';
}