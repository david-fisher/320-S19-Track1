<template>
  <div class="hello">
    <!-- Image -->
    <img src="../assets/logo.png">
    <!-- Header 1 -->
    <h1>{{ msg }}</h1>
    <!-- Header 2 -->
    <h2>You Know Why You're Here</h2>
    <!-- Unordered List -->
    <ul>
      <!-- List item -->
      <li>
        <!-- Hyperlink -->
        <a
          href="#/login"
        >
          Login
        </a>
      </li>
      <br>
      <li>
        <a
          href="#/passwordreset"
          target="_blank"
        >
          Password Reset
        </a>
      </li>
      <br>
      <h3>The following links are temporary:</h3>
      <li>
        <a
          href="#/irv"
          target="_blank"
        >
          Information Requisition and Verification
        </a>
      </li>
      <!-- leaving this for reference -->
      <!--<li>
        <a
          href="https://vuejs.org"
          target="_blank"
        >
          Core Docs
        </a>
      </li>
      <li>
        <a
          href="https://forum.vuejs.org"
          target="_blank"
        >
          Forum
        </a>
      </li>
      <li>
        <a
          href="https://chat.vuejs.org"
          target="_blank"
        >
          Community Chat
        </a>
      </li>
      <li>
        <a
          href="https://twitter.com/vuejs"
          target="_blank"
        >
          Twitter
        </a>
      </li>
      <br>
      <li>
        <a
          href="http://vuejs-templates.github.io/webpack/"
          target="_blank"
        >
          Docs for This Template
        </a>
      </li>
    </ul>
    <h2>Ecosystem</h2>
    <ul>
      <li>
        <a
          href="http://router.vuejs.org/"
          target="_blank"
        >
          vue-router
        </a>
      </li>
      <li>
        <a
          href="http://vuex.vuejs.org/"
          target="_blank"
        >
          vuex
        </a>
      </li>
      <li>
        <a
          href="http://vue-loader.vuejs.org/"
          target="_blank"
        >
          vue-loader
        </a>
      </li>
      <li>
        <a
          href="https://github.com/vuejs/awesome-vue"
          target="_blank"
        >
          awesome-vue
        </a>
      </li>-->
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Welcome',
  data () {
    return {
      msg: 'Welcome to Members Only'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
