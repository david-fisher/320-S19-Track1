<template>
  <div align="center" style="border:1px solid black">
    <h1>Reset Password</h1>
    <form class="form" action="/sendResetCode" method="post" v-on:submit="sendCode($event)">
      <div>
        <div style="text-align: left">
          <label  class="label" align="left" style="margin-inline-start: 20%;" for="email"><b>Email: </b></label>
        </div>
        <div class="ui fluid input">
          <input type="email" v-model="form.email" placeholder="Email" id="email" required>
        </div>
      </div>
      <br>
      <div>
        <button type="submit" style="width:200px; margin-bottom:5px; color:#D6A200" class="ui black button">Send Verification Code</button>
      </div>
    </form>
    <br>
    <form class="form" action="/updatePassword" method="post" v-on:submit="sendPass($event)">
      <div style="text-align: left">
        <label  class="label" align="left" style="margin-inline-start: 20%;"><b>Info: </b></label>
      </div>
      <div class="ui fluid input">
        <input v-model="form.code" placeholder="Verification Code" id="vcode" required>
        <input type="password" v-model="form.pass" placeholder="New password" id="pass" required>
        <input type="password" v-model="form.confPass" placeholder="Confirm password" id="cpass" required>
      </div>
      <br>
      <div>
        <button type="submit" style="width:200px; margin-bottom:5px; color:#D6A200" class="ui black button">Reset Password</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      form: {
        email: '',
        pass: '',
        confPass: ''
      }
    }
  },
  methods: {
    sendCode: function(event){
      if(!sendResetCode(this.form.email)){
        event.preventDefault()
      }

    },
    sendPass: function(event){
      if(!updatePassword(this.form)){
        event.preventDefault()
      }
    }
  }
}

function submit(form) {
  // Send data to the server and display the resultant message
  var sendData = form.email
  if(storeInfo(sendData).length == 0){
    return true
  }
  return false
}

function sendResetCode(email){
  return /\S+@\S+\.\S+/.test(email)
}

function updatePassword(form){
  if(form.pass == form.confPass){
    return true;
  }
  return false;
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
