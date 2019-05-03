<template>
  <div>
    <div align="center" style="border:1px solid black">
      <h1>Account Creation</h1>


      <form class="ui form" action="/storeInfo" method="post" v-on:submit="verifyForm($event)">
        <div class="ui stacked segment">
          <div class="field">
            <div class="ui left icon input">
              <input type="password" placeholder="Password" v-model="form.pass" id="password" required>
              <i class="lock icon"></i>
            </div>
          </div>
          <div class="field">
            <div class="ui left icon input">
              <input type="password" placeholder="Confirm Password"  v-model="form.confirmPass" id="confirmPassword" required>
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
    </div>
  </div>
  
</template>

<script>
export default {

  name: 'HelloWorld',
  data () {
    return {
      form: {
        pass: '',
        confirmPass: ''
      },
      submitText: ''
    }
  },
  methods: {
    passwordsEqual: function(){
      return this.form.pass == this.form.confirmPass
    },
    verifyPassword: function(event){
      event.preventDefault()
      if(!this.passwordsEqual()){
        this.submitText = "Yeah, those passwords don't match. Try again." 
      } else if(this.pass.length < 8){
        this.submitText = "Nuh uh. Your password must be at least eight characters"
      } else {

        const path = this.ip + '/storePassword'
  		
        this.$http.post(path, this.form)
        .then(response => {
          console.log(response)
          var retVal = JSON.parse('{' + response.bodyText)
          if(retVal.result.length == 0){
            if(this.$session.exists()){
              this.$session.set('password', this.form.pass)
            } else {
              this.$router.push('/')
            }
            this.submitText = "One moment..."
            this.$router.push('/feed')
          } else {
            this.submitText = retVal.result
          }
        })
        .catch(error => {
          console.log("Yeah nope")
          console.log(error)
        })
      }
    }
  }
}

function PasswordsEqual(form){
  console.log(form.pass == form.confirmPass);
  return (form.pass == form.confirmPass);
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
