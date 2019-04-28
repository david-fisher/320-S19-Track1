<template>
  <div align="center" style="border:1px solid black">
    <h1>Reset Password</h1>
    <form class="form" v-on:submit="sendCode($event)">
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
    <form class="form" v-on:submit="sendPass($event)">
      <div style="text-align: left">
        <label  class="label" align="left" style="margin-inline-start: 20%;"><b>Info: </b></label>
      </div>
      <div class="ui fluid input">
        <input v-model="form2.code" placeholder="Verification Code" id="vcode" required>
        <input type="password" v-model="form2.pass" placeholder="New password" id="pass" required>
        <input type="password" v-model="form2.confPass" placeholder="Confirm password" id="cpass" required>
      </div>
      <br>
      <div>
        <button type="submit" :disabled="disabled" style="width:200px; margin-bottom:5px; color:#D6A200" class="ui black button">Reset Password</button>
      </div>
      <div class="submission check">
        <p style="color:#FF0000">{{ submitText }}</p>
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
        email: ''
      },
      form2: {
        code: '',
        pass: '',
        confPass: ''
      },
      disabled: true,
      submitText: ''
    }
  },
  methods: {
    checkEmail: function(){
      return /\S+@\S+\.\S+/.test(this.form.email)
    },


    sendResetCode: function(){
      const path = this.ip + '/sendResetCode'

      this.$http.post(path, this.form)
      .then(response => {
        var retVal = JSON.parse('{'+response.bodyText)
        console.log(retVal)

        //All is well
        if(retVal.result.length == 0){
          this.submitText = "Code Sent!"
          this.disabled = false
        } else {
          this.submitText = "somethin"
        }
      })
      .catch(error => {
        console.log("Yeah nope")
        this.submitText = "Error processing request. Please try again."
        console.log(error)
      })

    },


    submit: function(){
      if(!this.checkEmail()){
        this.form.submitText = 'Invalid email'
      } else {
        this.sendResetCode()
      }
    },


    sendCode: function(event){
      event.preventDefault()
      this.submit()
    },


    sendPass: function(event){
      event.preventDefault()
      const path = this.ip +'/updatePassword'

      this.$http.post(path, this.form)
      .then(response => {
        var retVal = JSON.parse('{' + response.bodyText)
        console.log(retVal)
        if(retVal.result.length == 0){
          this.submitText = "You should be redirected shortly... ALSO CHANGE THIS LATER"
          this.$router.push('/')
        } else {
          this.submitText = "Password was unable to be updated"
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
