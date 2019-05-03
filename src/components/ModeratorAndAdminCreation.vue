
<template>
<div>
  
  <div class="ui true grey fixed inverted menu">
    <div class="ui container">
      <a class="header item" href="#">
        <img src="../assets/light_logo.png" class="logo">
          Members Only
      </a> 

      <a class="item" href="#Feed">
        Feed
      </a> 

      <div role="listbox" class="simple ui item dropdown">
        <div role="alert" aria-live="polite" class="text">Account
          </div>
        <i aria-hidden="true" class="dropdown icon"></i>
        <span class="sizer">
        </span>
        <div class="menu transition">
          <a role="option" class="item" href="#TODOPUTPROFILEHERE">
            My Profile
          </a>
          <a role="option" class="item" href="#TODO">
            Generate Invite Link
          </a> 
          <a role="option" class="item" href="#TODO">
            Settings
          </a>
          </div>
        </div>
      </div>
    </div>

    <!-- ;) -->
    <br>
    <br>
    <br>
    <br>

    <div align="center" style="border:1px solid black">
    <h1>Member and Admin Creation Page</h1>
    <form class="form" action="/WebServ/dist/#/storeInfo" method="post" v-on:submit="verifyForm($event)">
      <div>
        <select v-model="form.role">
          <option>Moderator</option>
          <option>Admin</option>
          <option>Owner</option>
          <option>Idol</option>
        </select>
      </div>
      <br>

        <div class="field">
            <div class="ui left icon input">
              <input type="email" placeholder="Email" v-model="form.email" id="email" required>
              <i class="user icon"></i>
            </div>
          </div>
          <div class="field">
            <div class="ui left icon input">
              <input type="password" placeholder="Password"  v-model="form.pass" id="password" required>
              <i class="lock icon"></i>
            </div>
        </div>
      <br>
      <div class="submission check">
        <p style="color:#FF0000">{{ submitText }}</p>
      </div>
      <br>
      <div>
        <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Create</button>
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
        role: '',
        email: '',
        pass: ''
      },
      submitText: ''
    }
  },
  methods: {
    verifyForm: function(event){
      event.preventDefault();
      if(!this.$session.exists()){
        this.$router.push('/')
        return
      }
      console.log(this.form.role)
      console.log(this.form.email)
      console.log(this.form.pass)

      if(this.form.role.length == 0){
        this.submitText = "You cannot leave role blank. Please make a selection."
      } else if(this.form.email.length == 0){
        this.submitText = "You cannot leave email blank. Please input the user's email."
      } else if(this.form.pass.length < 8){
        this.submitText = "Passwords must be at least 8 characters long."
      } else {
        const path = this.ip + '/createUser'
      
        const data = {
          form: this.form,
          email: this.$session.get('email'),
          password: this.$session.get('password'),
        }

        this.$http.post(path, data)
        .then(response => {
          console.log(response)
          var retVal = JSON.parse('{' + response.bodyText)
          if(retVal.result.length == 0){
            this.submitText = "Account created!"
          } else {
            this.submitText = retVal.result + " is invalid. You must not leave this blank, and it must be valid."
          }
        })
        .catch(error => {
          console.log("Yeah nope")
          console.log(error)
          this.submitText = "There was some kind of communication error. Not sure why. Must be your fault though."
        })
      }
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
