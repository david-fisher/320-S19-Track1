<template>
  <div align="center" style="border:1px solid black">
    <h1>Password Reset</h1>
    <form>
        <label for="email"><b>Email</b></label>
        <input type="email" placeholder="Email" name="email" required>

        <button type="submit">Reset Password</button>
    </form>
  </div>
</template>

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
