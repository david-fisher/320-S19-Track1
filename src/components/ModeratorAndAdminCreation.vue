
<template>

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
      <div class="ui fluid input">
        <label class="label" for="email"><b>Email: </b></label>
        <input type="text" v-model="form.email" placeholder="Email" id="email" required>
      </div>
      <br>
      <div class="ui fluid input">
        <label class="label" for="password"><b>Password: </b></label>
        <input type="password" v-model="form.pass" placeholder="Password" id="password" required>
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
