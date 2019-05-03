<template>
  <div align="center" style="border:1px solid black">
    <h1>Settings</h1>
    <form class="form" v-on:submit="verifyFormCC($event)">
      <br>
      <div class="field">
        <label class="label">Update Credit Card</label>
      </div>
      <div class="ui fluid input">
        <input type="text" v-model="form1.creditCardNumber" placeholder="Credit Card Number" id="creditcardnumber" required>
        <input type="text" v-model="form1.expiration" placeholder="Expiration (MM/YY)" id="expiration" required>
        <input type="text" v-model="form1.CVV" placeholder="CVV" id="CVV" required>
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
          <select v-model="form3.whoCanSeeMe">
            <option disabled value="">Who can see my posts.</option>
            <option value="everyone">Everyone</option>
            <option value="followingMe">Only people who follow me (and invitees)</option>
          </select>
          <select v-model="form3.whoDoISee">
            <option disabled value="">Whose posts do I see.</option>
            <option value="everyone">Everyone</option>
            <option value="thoseIFollow">Only those I follow</option>
          </select>
        </div>
      </div>
      <br>
      <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Submit</button>
      <div class="submission check">
        <p style="color:#FF0000">{{ form3SubmitText }}</p>
      </div>
      <br>
    </form>
    <form class="form" v-on:submit="changePhoto($event)">
      <div class="field">
        <label class="label">Profile Photo:</label>
      </div>
      
      <input type="file" id="imageLoader" @change="onFileChanged"/>
      <canvas id="imageCanvas" ref="imageCanvas"></canvas>
      
      <br>
      <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Submit</button>
      <div class="submission check">
        <p style="color:#FF0000">{{ form4SubmitText }}</p>
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
      form4: {
        photo: null
      },
      form4SubmitText: '',
      inviteLink: ''
    }
  },
  beforeCreate: function(){
    console.log(this.$session.get('email'))
    if(!this.$session.exists()){
      this.$router.push('/')
    }
    const path = this.ip + '/pullPicture'
        
    const data = {
      email: this.$session.get('email'),
      password: this.$session.get('password')
    }

    this.$http.post(path, data)
    .then(response => {
      console.log(response)
      var retVal = JSON.parse('{' + response.bodyText)
      if(retVal.result.length == 0){
        //Error or something
      } else if(retVal.result == "error"){
        console.log('BAD')
      }
      else {
        var canvas = this.$refs.imageCanvas
        var ctx = canvas.getContext('2d')
        var img = new Image
        img.onload = function(){
          ctx.drawImage(img,0,0)
        }
        canvas.width = this.maxImageWidth
        canvas.height = this.maxImageHeight
        img.src = retVal.result
      }
    })
    .catch(error => {
      console.log("Yeah nope")
      console.log(error)
    })
  },
  methods: {
    changePhoto: function(){
      var canvas = this.$refs.imageCanvas
      var thing = {
        photo: canvas.toDataURL()
      }

      const path = this.ip + '/changePhoto'
      
      console.log(thing.photo)

      const data = {
        form: thing,
        email: this.$session.get('email'),
        password: this.$session.get('password'),
      }

      this.$http.post(path, data)
      .then(response => {
        console.log(response)
        var retVal = JSON.parse('{' + response.bodyText)
        if(retVal.result.length == 0){
          this.form4SubmitText = "Updated!"
        } else {
          this.form4SubmitText = retVal.result + " is incorrect or blank. Plox Fix."
        }
      })
      .catch(error => {
        console.log("Yeah nope")
        console.log(error)
      })
    },
    drawCanvasImage: function(img) {
      var canvas = this.$refs.imageCanvas;

      if(img.width < img.height){
        canvas.width = this.maxImageWidth
        canvas.height = this.maxImageWidth
      } else {
        canvas.width = this.maxImageHeight
        canvas.height = this.maxImageHeight
      }
      var ctx = canvas.getContext('2d');
      ctx.drawImage(img, 0, 0, this.maxImageWidth, this.maxImageHeight);
    },
    onFileChanged: function(e){
      var self = this;
      var reader, files = e.target.files;
      var reader = new FileReader();
      reader.onload = (e) => {
          var img = new Image();
          img.onload = function() {
              self.drawCanvasImage(img)
          }
          img.src = event.target.result;
      };
      reader.readAsDataURL(files[0]);
    },
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
        const path = this.ip + '/updateCCAddress'
        
        const data = {
          form: this.form1,
          email: this.$session.get('email'),
          password: this.$session.get('password'),
        }

        this.$http.post(path, data)
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

      const path = this.ip + '/updateProfileDescription'
      
        const data = {
          form: this.form2,
          email: this.$session.get('email'),
          password: this.$session.get('password'),
        }

        this.$http.post(path, data)
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

      const path = this.ip + '/updateVisibility'

      const data = {
        form: this.form3,
        email: this.$session.get('email'),
        password: this.$session.get('password'),
      }

      this.$http.post(path, data)
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
      const path = this.ip + '/generateInvite'
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