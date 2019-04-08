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
        <form class="form">
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
          <div class="submit button">
            <button class="ui black button" style="color:#D6A200" v-on:click="submit">
              Submit
            </button>
          </div>
          <div class="submission check">
            <p style="color:#FF0000">{{ form.submitText }}</p>
          </div>
          <!-- Leaving this in for reference:
          <div class="field">
            <label class="label">Message</label>
            <div class="control">
              <textarea class="textarea" placeholder="Textarea" v-model="form.message"></textarea>
            </div>
          </div>

          <div class="field">
            <label class="label">Inquiry Type</label>
            <div class="control">
              <div class="select">
                <select v-model="form.inquiry_type">
                  <option disabled value="">Nothing selected</option>
                  <option v-for="option in options.inquiry" v-bind:value="option.value" :key="option.id">
                    {{ option.text }}
                  </option>
                </select>
              </div>
            </div>
          </div>

          <div class="field">
            <label class="label">LogRocket Usecases</label>
            <div class="control">
              <div class="select is-multiple">
                <select multiple v-model="form.logrocket_usecases">
                  <option>Debugging</option>
                  <option>Fixing Errors</option>
                  <option>User support</option>
                </select>
              </div>
            </div>
          </div>

          <div class="field">
            <div class="control">
              <label class="checkbox">
                <input type="checkbox" v-model="form.terms">
                I agree to the <a href="#">terms and conditions</a>
              </label>
            </div>
          </div>

          <div class="field">
            <label>
              <strong>What dev concepts are you interested in?</strong>
            </label>
            <div class="control">
              <label class="checkbox">
                <input type="checkbox" v-model="form.concepts" value="promises">
                Promises
              </label>
              <label class="checkbox">
                <input type="checkbox" v-model="form.concepts" value="testing">
                Testing
              </label>
            </div>
          </div>

          <div class="field">
            <label><strong>Is JavaScript awesome?</strong></label>
            <div class="control">
              <label class="radio">
                <input v-model="form.js_awesome" type="radio" value="Yes">
                Yes
              </label>
              <label class="radio">
                <input v-model="form.js_awesome" type="radio" value="Yeap!">
                Yeap!
              </label>
            </div>
          </div>-->
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
// Create WebSocket connection.
const socket = new WebSocket('ws://localhost:9999')

// Connection opened
socket.addEventListener('open', function (event) {
    socket.send('Hello Server!')
});

// Listen for messages
socket.addEventListener('message', function (event) {
    var tokens = event.data.split(' ')
    console.log('Message from server ', event.data)
});

export default {
  name: 'InformationRequisitionAndVerification',
  data () {
    return {
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
        submitText: ''/*,
        inquiry_type: '',
        logrocket_usecases: [],
        terms: false,
        concepts: [],
        js_awesome: '' */
      },
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
  methods: {
    submit: function (event) {
      // `this` inside methods points to the Vue instance
      // Verify account data and submit
      if (!validPhone(this.form.phoneNumber)) {
        this.form.submitText = 'You must fill in a valid phone number!'
      } else if (!validEmail(this.form.email)) {
        this.form.submitText = 'You must fill in a valid email!'
      }
      else{
        // Send data to the server and display the resultant message
        var sendData = ''
        sendData += this.form.firstName + ' '
        sendData += this.form.lastName + ' '
        sendData += this.form.addressLineOne + ' '
        sendData += this.form.addressLineTwo + ' '
        sendData += this.form.city + ' '
        sendData += this.form.state + ' '
        sendData += this.form.zipCode + ' '
        sendData += this.form.phoneNumber + ' '
        sendData += this.form.email + ' '
        sendData += this.form.creditCardNumber + ' '
        sendData += this.form.expiration + ' '
        sendData += this.form.CVV
        if(storeInfo(sendData).length == 0){
          alert('Valid data')
        }
      }
    }
  }
}

function validPhone(number){
  if(/^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/.test(number)){
    return true
  }
  return false
}

function validEmail(email){
  return /\S+@\S+\.\S+/.test(email)
}

function storeInfo(inputs){
  socket.send('storeInfo' + ' ' + inputs)
  return ''
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
