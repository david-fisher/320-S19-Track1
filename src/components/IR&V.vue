<template>
  <div class="columns" id="app">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <div class="column is-two-thirds">
      <section class="section">
        <h1 class="title">Members Only</h1>
        <p class="subtitle">
          Information Requisition and Verification!
        </p>
        <hr>
        <!-- form starts here -->
        <form class="form" v-on:submit="verifyForm($event)">
          <div class="field">
            <label class="label">Name:</label>
            <br>
            <div class="ui fluid input">
              <input name="firstName" v-model="form.firstName" class="input" type="text" placeholder="First" required/>
              <input name="lastName" v-model="form.lastName" class="input" type="text" placeholder="Last" required/>
            </div>
          </div>
          <div class="field">
            <label class="label">Address:</label>
            <br>
            <div class="ui fluid input">
              <input name="addressLineOne" v-model="form.addressLineOne" class="input" type="text" placeholder="Address Line 1" required>
              <input name="addressLineTwo" v-model="form.addressLineTwo" class="input" type="text" placeholder="Address Line 2">
            </div>
            <div class="ui fluid input">
              <input name="city" v-model="form.city" class="input" type="text" placeholder="City" required>
              <input name="state" v-model="form.state" class="input" type="text" placeholder="State" required>
              <input name="zipCode" v-model="form.zipCode" class="input" type="text" placeholder="Zip Code" required>
            </div>
          </div>
          <div class="field">
            <label class="label">Phone:</label>
            <br>
            <div class="ui input">
              <input name="phoneNumber" v-model="form.phoneNumber" id="phone" class="input" type="text" placeholder="Phone (US)" required>
            </div>
          </div>
          <div class="field">
            <label class="label">Email:</label>
            <br>
            <div class="ui input">
              <input name="email" v-model="form.email" class="input" type="text" placeholder="Email" required>
            </div>
          </div>
          <div class="field">
            <label class="label">Credit Card:</label>
            <br>
            <div class="ui fluid input">
              <input name="creditCardNumber" v-model="form.creditCardNumber" class="input" type="text" placeholder="Credit Card Number" required>
              <input name="expiration" v-model="form.expiration" class="input" type="text" placeholder="Expiration (MM/YY) Maybe Use Dropdowns?" required>
              <input name="CVV" v-model="form.CVV" class="input" type="text" placeholder="CVV" required>
            </div>
          </div>
          <br>
          <div class="field">
            <label class="label">Registration Code:</label>
            <br>
            <div class="ui fluid input">
              <input name="registrationCode" v-model="form.registrationCode" class="input" type="text" placeholder="Registration Code" required>
            </div>
          </div>
          <br>
          <div class="field">
            <label class="label">Password:</label>
            <br>
            <div class="ui fluid input">
              <input name="password" v-model="form.password" class="input" type="text" placeholder="Password" required>
              <input name="confpass" v-model="form.confpass" class="input" type="text" placeholder="Confirm Password" required>
            </div>
          </div>
          <br>
          <div class="submit button">
            <input class="ui black button" style="color:#D6A200" type="submit" value="Submit">
          </div>
          <div class="submission check">
            <p style="color:#FF0000">{{ submitText }}</p>
          </div>
        </form> <!--Ends the input form-->
        <br>
        <form class="form" v-on:submit="verifyCharge($event)">
          <div class="field">
            <div class="ui input">
              <input name="charge" v-model="charge" class="input" type="text" placeholder="Charge Amount" required>
            </div>
          </div>
          <br>
          <div class="submit button">
            <input class="ui black button" style="color:#D6A200" :disabled="disabled == true" type="submit" value="Verify Charge and Continue">
          </div>
        </form>
      </section>
    </div>

    <div class="column">
      <section class="section" id="results">
        <div class="box">
          <ul>
            <!-- loop through all the `form` properties and show their values -->
            <li v-for="(item, k) in form" :key="item">
                <strong>{{ k }}:</strong> {{ item }}
            </li>
          </ul>
        </div>
      </section>
    </div>
  </div>
</template>s

<script>

export default {
  name: 'InformationRequisitionAndVerification',
  data () {
    return {
      serverAddress: '',
      form: {
        firstName: '',
        lastName: '',
        addressLineOne: '',
        addressLineTwo: '',
        city: '',
        state: '',
        zipCode: '',
        phoneNumber: '',
        email: '',
        creditCardNumber: '',
        expiration: '',
        CVV: '',
        registrationCode: '',
        password: '',
        confpass: ''
      },
      disabled: true,
      submitText: '',
      charge: '',
      // needed for the unused options
      options: {
        inquiry: [
          {value: 'feature', text: 'Feature Request'},
          {value: 'bug', text: 'Bug Report'},
          {value: 'support', text: 'Support'}
        ]
      }
    }
  },
  mounted() {
    //Method to run on page load goes here.
  },
  methods: {
    verifyCharge: function(event){
      event.preventDefault()
      if(/^0?.[0-9]{2}$/.test(this.charge)){
        const path = this.ip + '/chargeVerify'

        const data = {
          charge: this.charge,
          email: this.form.email,
          password: this.form.password
        }

        this.$http.post(path, data)
        .then(response => {
          var retVal = JSON.parse('{' + response.bodyText)
          if(retVal.result.length == 0){
            this.submitText = "You should be redirected shortly..."
            this.$router.push('/login')
          } else {
            this.submitText = retVal.result + " is invalid. You must not leave this blank, and it must be valid."
          }
        })
        .catch(error => {
          console.log("Yeah nope")
          this.submitText = "Error processing request. Please try again."
          console.log(error)
        })
      } else{
        console.log("hi")
        this.submitText = "Format should be '0.XX' where XX represents the number of cents you were charged"
      }
    },
    validPhone: function(){
      return (/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/.test(this.form.phoneNumber))
    },
    validEmail: function(){
      return /\S+@\S+\.\S+/.test(this.form.email)
    },
    submit: function() {
      // `this` inside methods points to the Vue instance
      // Verify account data and submit
      if (!this.validPhone()) {
        this.submitText = 'You must fill in a valid phone number!'
      } else if (!this.validEmail()) {
        this.submitText = 'You must fill in a valid email!'
        console.log('wee')
      }
      else{
        this.storeInfo()
      }
    },
    passwordsEqual: function(){
      return this.form.password == this.form.confpass
    },
    storeInfo: function(){
      if(!this.passwordsEqual()){
        this.submitText = "Yeah, those passwords don't match. Try again." 
      } else if(this.form.password.length < 8){
        this.submitText = "Nuh uh. Your password must be at least eight characters"
      } else {

        const path = this.ip + '/storeInfo'
        
        this.$http.post(path, this.form)
        .then(response => {
          console.log(response)
          var retVal = JSON.parse('{' + response.bodyText)
          if(retVal.result.length == 0){
            this.submitText = "Your card has been charged. Please check the amount and enter it into the box below."
            this.disabled = false
          } else {
            this.submitText = retVal.result + " is invalid. You must not leave this blank, and it must be valid."
          }
        })
        .catch(error => {
          console.log("Yeah nope")
          console.log(error)
        })
      }
    },
    verifyForm: function(event){
      event.preventDefault()
      this.submit()
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
  clear: both;
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
