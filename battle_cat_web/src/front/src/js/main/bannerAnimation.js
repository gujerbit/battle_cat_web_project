export { slideBanner, fadeBanner };

function slideBanner() { //배너 슬라이드 시스템
  let imgs = document.querySelector('.slide').childNodes; //이미지들
  let idx = 0; //현재 가장 뒤로 갈 녀석의 인덱스
  let max = imgs.length; //이미지 최대 개수

  imgs.forEach((res, index) => res.style.left = `${index * 100}%`); //이미지를 전부 가로로 늘어놓는 초기작업

  setInterval(() => { //5초마다 실행
    if(idx > max - 1) idx = 0; //인덱스가 슬라이드 전체 개수를 초과하면 초기화

    imgs.forEach(res => res.style.left = `${res.style.left.split('%')[0] - 100}%`); //왼쪽에서 오른쪽으로 한 칸씩 이동

    setTimeout(() => { //2초 뒤에 실행
      imgs[idx].style.transition = 'none'; //맨 뒤로 갈 녀석의 트랜지션 제한
      imgs[idx].style.left = `${(imgs[idx].style.left.split('%')[0] * 1) + (max * 100)}%`; //가장 앞에 놈은 가장 뒤로 보냄
    }, 2000);
    
    setTimeout(() => { //1초 뒤에 실행
      imgs[idx].style.transition = 'all 2s'; //맨 뒤로 간 녀석의 트랜지션 재설정
      idx++; //인덱스 증가
    }, 3000);

  }, 4000);
}

function fadeBanner() {
  let imgs = document.querySelector('.slide').childNodes;
  let idx = 0;
  let max = imgs.length;
  // let origin = 0;
  // let spin = 180;

  imgs.forEach(res => res.style.opacity = '0');
  imgs[idx].style.opacity = '1';

  setInterval(() => {
    // origin += spin;
    // imgs[idx].style.transform = `skew(${origin}deg)`;
    imgs[idx].style.opacity = '0';
    idx++;
    if(idx > max - 1) idx = 0;
    // imgs[idx].style.transform = `skew(${origin}deg)`;
    imgs[idx].style.opacity = '1';
    // spin *= -1;
  }, 7000);
}