https://battle-cats.fandom.com/wiki/Battle_Cats_Wiki - 냥코 리소스 참조
https://github.com/kaodigua/vuex-persist-indexeddb - vuex indexeddb 참조

서버에 적용하는 법
1. ps -ef | grep battle로 실행되고 있는 jar PID 출력
2. sudo kill -15 해당 PID
3. battle_cat_web_project에서 sudo git pull origin master
4. build/libs에서 sudo rm -rf 기존 빌드 파일
5. battle_cat_web에서 sudo ./gradlew build
6. build/libs에서 sudo nohup java -jar 해당 빌드 파일 &

정보
	아군 캐릭터
		효과/능력
		타겟
		레어도
		고유 번호
	적 캐릭터
		속성
		효과/능력
		고유번호
	스테이지
		세계편
		미래편
		우주편
		레전드 스테이지
		신레전드 스테이지
		스페셜 스테이지
		콜라보 스테이지
	기타
		가마토토
		오토토
		냥콤보
커뮤니티

attack_type(attack_type1, start_damage_range, end_damage_range/attack_type2...) -> multi/wide,-500,300