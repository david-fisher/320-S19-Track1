<template>
  <div align="center" style="border:1px solid black">
    <h1>Settings</h1>
    <form class="form" v-on:submit="verifyFormCC($event)">
      <br>
      <div class="field">
        <label class="label">Update Credit Card</label>
      </div>
      <div class="ui fluid input">
        <input type="text" v-model="form.creditCardNumber" placeholder="Credit Card Number" id="creditcardnumber" required>
        <input type="text" v-model="form.expiration" placeholder="Expiration (MM/YY)" id="expiration" required>
        <input type="text" v-model="form.CVV" placeholder="CVV" id="CVV" required>
      </div>
      <br>
      <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Submit</button>
      <div class="submission check">
        <p style="color:#FF0000">{{ form1SubmitText }}</p>
      </div>
    </form>
    <form class="form" v-on:submit="verifyFormDesc($event)">
      <br>
      <div class="field">
        <label class="label">Update Description</label>
      </div>
      <div class="ui fluid input">
        <input type="description" v-model="form2.description" placeholder="Profile Description" id="description" required>
      </div>
      <br>
      <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Submit</button>
      <div class="submission check">
        <p style="color:#FF0000">{{ form2SubmitText }}</p>
      </div>
    </form>
    <form class="form" v-on:submit="verifyFormVis($event)">
      <br>
      <div class="field">
        <label class="label">Update Visibility</label>
      </div>
      <div class="control">
        <div class="select">
          <select v-model="form.whoCanSeeMe">
            <option disabled value="">Who can see my posts.</option>
            <option value="everyone">Everyone</option>
            <option value="followingMe">Only people who follow me (and invitees)</option>
          </select>
          <select v-model="form.whoDoISee">
            <option disabled value="">Whose posts do I see.</option>
            <option value="everyone">Everyone</option>
            <option value="thoseIFollow">Only those I follow</option>
          </select>
        </div>
      </div>
      <br>
      <div class="submission check">
        <p style="color:#FF0000">{{ form3SubmitText }}</p>
      </div>
      <br>
    </form>
    <br>
    <div>
      <button type="button" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button" v-on:click="generateInvite()">Generate Invite</button>
      <label class="label">"Invitation Link"</label>
    </div>
    <br>
    <div class="submission check">
      <p style="color:#000000">{{ inviteLink }}</p>
    </div>
    <br>
    <div>
      <button type="button" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Feed</button>
      <button type="button" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Profile</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Settings',
  data () {
    return {
      form1: {
        creditCardNumber: '',
        expiration: '',
        CVV: ''
      },
      form1SubmitText: '',
      form2: {
        description: ''
      },
      form2SubmitText: '',
      form3: {
        whoCanSeeMe: '',
        whoDoISee: ''
      },
      form3SubmitText: '',
      inviteLink: ''
    }
  },
  methods: {
    verifyFormCC: function(event){
      event.preventDefault()
      if(this.form1.creditCardNumber.length == 0){
        this.form1SubmitText = "Credit card number cannot be blank!"
      } else if(this.form1.expiration.length == 0){
        this.form1SubmitText = "Expiration cannot be blank!"
      } else if(this.form1.CVV.length == 0){
        this.form1SubmitText = "CVV cannot be blank!"
      } else{
        //Some fancy networking shit
        this.$http.post(path, this.form1)
        .then(response => {
          console.log(response)
          var retVal = JSON.parse('{' + response.bodyText)
          if(retVal.result.length == 0){
            this.form1SubmitText = "Updated!"
          } else {
            this.form1SubmitText = retVal.result + " is incorrect or blank. Plox Fix."
          }
        })
        .catch(error => {
          console.log("Yeah nope")
          console.log(error)
        })
      }
    },
    verifyFormDesc: function(event){
      event.preventDefault()
      this.$http.post(path, this.form2)
      .then(response => {
        console.log(response)
        var retVal = JSON.parse('{' + response.bodyText)
        if(retVal.result.length == 0){
          this.form2SubmitText = "Updated!"
        } else {
          this.form2SubmitText = "Not even sure what happened, but try again...?"
        }
      })
      .catch(error => {
        console.log("Yeah nope")
        console.log(error)
      })
    },
    verifyFormVis: function(event){
      event.preventDefault()
      this.$http.post(path, this.form3)
      .then(response => {
        console.log(response)
        var retVal = JSON.parse('{' + response.bodyText)
        if(retVal.result.length == 0){
          this.form3SubmitText = "Updated!"
        } else {
          this.form3SubmitText = "Invalid Values. Stop trying to break my API."
        }
      })
      .catch(error => {
        console.log("Yeah nope")
        console.log(error)
      })
    },
    generateInvite: function(){
      this.$http.post(path)
      .then(response => {
        console.log(response)
        var retVal = JSON.parse('{' + response.bodyText)
        if(retVal.result.length == 0){
          this.inviteLink = "Invite failed to generate. Perhaps you already have one?"
        } else {
          this.inviteLink = "Your invite link is: " + retVal.result
        }
      })
      .catch(error => {
        console.log("Yeah nope")
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