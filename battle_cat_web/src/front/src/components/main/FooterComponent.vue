<template>
  <footer>
    <div class="content">
      <p>this is battle cat KR info {{'&'}} community page &copy; gujerbit all rights reserved.</p>
      <p>current version 1.5.2-r</p>
    </div>
  </footer>
</template>

<script>
export default {
    
}
</script>

<style scoped>
footer {
  width: 100%;
  height: 6%;
  background-color: #ffffff;
  border-top: 1.5px solid #ffc038;
  position: fixed;
  bottom: -5.25%;
  left: 0;
  opacity: 0;
  transition: all 1s;
}

footer:hover {
  bottom: 0;
  opacity: 1;
}

.content {
  width: 65%;
  margin: 0 auto;
  text-align: center;
  pointer-events: none;
}

.content p {
  color: #000000;
  font-size: 1.8rem;
}
</style>