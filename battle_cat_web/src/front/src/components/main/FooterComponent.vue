<template>
  <footer>
    <div class="content">
      <p>this is battle cat KR info {{'&'}} community page &copy; gujerbit all rights reserved.</p>
      <p>current version 1.4.1</p>
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
  height: 5%;
  background-color: #ffc038;
  position: fixed;
  bottom: -4.5%;
  left: 0;
  opacity: 0;
  transition: all 2s;
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
  color: #ffffff;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 1rem;
}
</style>