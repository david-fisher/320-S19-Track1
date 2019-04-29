<template>
  <div align="center" style="border:1px solid black">
    <h1>Settings</h1>
    <form class="form" action="/storeInfo" method="post" v-on:submit="verifyForm($event)">
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
      <br>
      <div class="field">
        <label class="label">Update Description</label>
      </div>
      <div class="ui fluid input">
        <input type="description" v-model="form.description" placeholder="Profile Description" id="description" required>
      </div>
      <br>
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
      <br>
      <div>
        <button type="button" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Generate Invite</button>
        <label class="label">"Invitation Link"</label>
      </div>
      <br>
      <br>
      <div>
        <button type="button" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Feed</button>
        <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Submit</button>
        <button type="button" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Profile</button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Settings',
  data () {
    return {
      form: {
        creditCardNumber: '',
        expiration: '',
        CVV: '',
        description: '',
        whoCanSeeMe: '',
        whoDoISee: ''
      }
    }
  },
  methods: {
    verifyForm: function(event){
      if(!submit(this.form)){
        event.preventDefault()
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