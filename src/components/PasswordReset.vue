<template>
  <div align="center" style="border:1px solid black">
    <h1>Reset Password</h1>
    <form class="form" action="/storeInfo" method="post" v-on:submit="resetPassword($event)">
      <div class="ui fluid input">
        <label class="label" for="email"><b>Email: </b></label>
        <input type="email" v-model="form.name" placeholder="Email" id="email" required>
      </div>
      <br>
      <div>
        <button type="submit" style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button">Reset Password</button>
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
        name: '',
        pass: ''
      }
    }
  },
  methods: {
    resetPassword: function(event){
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
