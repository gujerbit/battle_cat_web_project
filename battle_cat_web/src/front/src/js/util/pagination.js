export { pageDivision, pagination };
//페이지 번호 세팅
function pageDivision(arr, divisionPage) { //원본 배열, 한 화면에 보여질 페이지 번호 수
  let totalPage = []; //전체 페이지 번호 배열
  let tempArr = []; //하나의 페이지 번호 배열
  let maxPage = Math.ceil(arr.length / divisionPage); //최대로 나올 수 있는 페이지 개수
  
  for(let i = 1; i <= maxPage; i++) { //1페이지부터 최대 페이지까지
    tempArr.push(i); //한 페이지에 번호를 하나씩 추가해준다

    if(i % divisionPage === 0 || i === maxPage) { //한 화면에 보여질 페이지 번호 수까지 i가 도달했거나 i가 최대 페이지까지 도달했다면
      totalPage.push(tempArr); //전체 페이지에 한 페이지 배열을 넣어줌
      tempArr = []; //다음 페이지로
    }
  }
  
  return totalPage;
}
//페이지네이션
function pagination(arr, currentPage, divisionPage) { //원본 배열, 현재 페이지, 한 화면에 보여질 페이지 번호 수
  let currentView = []; //현재 번호에 보여질 내용들
  currentPage = (currentPage - 1) * divisionPage; //몇번째 내용부터 보여질건지

  if(currentPage > arr.length) currentPage = 0;

  for(let i = currentPage; i < currentPage + divisionPage; i++) { //설정한 인덱스부터 한 번호에 보여질 최대 내용까지
    currentView.push(arr[i]); //내용 하나씩 현재 번호에 보여질 내용들에 추가
    
    if(i >= arr.length - 1) break; //최대 내용보다 i가 더 많다면 종료
  }

  return currentView;
}