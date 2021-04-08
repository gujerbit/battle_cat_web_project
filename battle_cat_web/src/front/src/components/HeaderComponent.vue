<template>
    <header>
        <div id="header-logo">
            <img src="/../../imgs/temp/header_logo.png" alt="">
        </div>
        <nav id="header-menu">
            <ul class="header-menu-main">
                <li id="menu-info">
                    <p>정보</p>
                    <ul class="header-menu-sub">
                        <li>
                            <router-link to="" class="sub-menu">아군 캐릭터</router-link>
                        </li>
                        <li>
                            <router-link to="" class="sub-menu">적 캐릭터</router-link>
                        </li>
                        <li>
                            <router-link to="" class="sub-menu">스테이지</router-link>
                        </li>
                        <li>
                            <router-link to="" class="sub-menu">기타</router-link>
                        </li>
                    </ul>
                </li>
                <li id="menu-communicate">
                    <router-link to="" class="main-menu">커뮤니티</router-link>
                </li>
                <li id="menu-search">
                    <p>검색</p>
                    <ul class="header-menu-sub">
                        <li>
                            <router-link to="" class="sub-menu">정보 검색</router-link>
                        </li>
                        <li>
                            <router-link to="" class="sub-menu">유저 검색</router-link>
                        </li>
                    </ul>
                </li>
            </ul>
        </nav>
    </header>    
</template>

<script>
export default {
    
}
</script>

<style scoped>

</style>