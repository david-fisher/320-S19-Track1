<template>
  <div>
    <h2 class="ui black header">
      <img src="../assets/light_logo.png" class="ui image">
      <div class="content">
        Login Page
      </div>
    </h2> 
    <form class="ui form" v-on:submit="verifyForm($event)">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <input type="email" placeholder="Email" v-model="form.email" id="email" required>
            <i class="user icon"></i>
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <input type="password" placeholder="Password"  v-model="form.password" id="password" required>
            <i class="lock icon"></i>
          </div>
        </div>
        <div class="submission check">
          <p style="color:#FF0000">{{ submitText }}</p>
        </div>
        <button type="submit" style="color:#D6A200" class="ui black fluid large button">
          Login
        </button>
      </div>
    </form>
    <div>
    <a href="#">
      Back
    </a>
    </div>
  </div>

  <!-- <div align="center" style="border:1px solid black">
    <h1>Login Page</h1>
    <form class="form" v-on:submit="verifyForm($event)">
      <div class="ui fluid input">
        <label class="label" for="email"><b>Email: </b></label>
        <input type="text" v-model="form.email" placeholder="Email" id="email" required>
      </div>
      <br>
      <div class="ui fluid input">
        <label class="label" for="password"><b>Password: </b></label>
        <input type="password" v-model="form.password" placeholder="Password" id="password" required>
      </div>
      <br>
      <div class="submission check">
        <p style="color:#FF0000">{{ submitText }}</p>
      </div>
      <br>
      <div>
        <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Login</button>
      </div>
    </form>
  </div> -->
</template>

<script>
export default {
  data () {
    return {
      form: {
        email: '',
        password: ''
      },
      submitText: ''
    }
  },
  methods: {
    verifyForm: function(event){
      event.preventDefault()
      
      const path = this.ip + '/login'

      this.$http.post(path, this.form)
      .then(response => {
        var retVal = JSON.parse('{' + response.bodyText)
        console.log(retVal)
        if(retVal.result.length == 0){
          this.submitText = "You should be redirected shortly... ALSO CHANGE THIS LATER"
          this.$router.push('/')
        } else {
          this.submitText = "Email or Password is invalid."
        }
      })
      .catch(error => {
        console.log("Yeah nope")
        this.submitText = "Error processing request. Please try again."
        console.log(error)
      })
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
  color: #8F721B;
}
.form {
  clear: both;
}
.form .ui.fluid.input{
  margin-inline-start: 20%;
  margin-inline-end: 20%;
  vertical-align: middle;
  /*display: table-cell;*/
  height: 50px;
}
.form .ui.fluid.input .input {
  margin-left: 0.1%;
  margin-right: 0.1%;
  clear: both;
}
.form .field{
  align-items: right;
}
.form .label{
  font-weight: bold;
}
</style>
