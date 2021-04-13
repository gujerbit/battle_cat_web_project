<template>
    <header>
        <div class="header-container">
            <div class="header-logo">
                <img src="/../../imgs/temp/header_logo.png" alt="">
            </div>
            <div class="header-menu">
                <nav class="menu-bar">
                    <ul class="main-menu-bar">
                        <li>
                            <router-link to="">아군 캐릭터 정보</router-link>
                        </li>
                        <li>
                            <router-link to="">적군 캐릭터 정보</router-link>
                        </li>
                        <li>
                            <router-link to="">스테이지 정보</router-link>
                        </li>
                        <li>
                            <router-link to="">기타 정보</router-link>
                        </li>
                        <li>
                            <router-link to="">커뮤니티</router-link>
                        </li>
                        <li>
                            <router-link to="">정보/유저 검색</router-link>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="header-user">
                <router-link to="">로그인</router-link>
                <router-link to="">회원가입</router-link>
            </div>
        </div>
    </header>    
</template>

<script>
export default {
    
}
</script>

<style scoped>

</style>