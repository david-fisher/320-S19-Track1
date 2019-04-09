<template>
  <div align="center" style="border:1px solid black">
    <h1>Login</h1>
    <form>
        <label for="username"><b>Username</b></label>
        <input type="text" placeholder="Username" name="username" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Password" name="password" required>

        <button type="submit">Login</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'This is a Test'
    }
  }
}
</script>

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
