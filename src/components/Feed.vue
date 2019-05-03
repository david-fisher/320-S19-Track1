<template>
  <div id="bigDad">
    <div class="ui inverted segment">
      <div class="ui inverted secondary menu">
        <div class="ui icon input">
          <form v-on:submit="search($event)">
            <input type="text" v-model="search" placeholder="Search members...">
            <i class="search icon"></i>
          </form>
        </div>
        <img class="item right" src="../assets/dark_logo.png" alt="Smiley face" height="60" width="60">
        <a class="item right" >
          <i class="user circle outline icon"></i>
        </a>
        <a class="active item">
          <i class="align justify icon"></i>
        </a>
      </div>
    </div>
    <div class="makePost">
        <form v-on:submit="postImage($event)" class="ui form">
          <input type="file" id="imageLoader" @change="onFileChanged"/>
          <canvas id="imageCanvas" ref="imageCanvas"></canvas>
          <input  style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button" type="submit" value="Post"> 
        </form>
        <form v-on:submit="postText($event)" class="ui form">
          <textarea v-model="postTextJSON.message" cols="7" rows="5" charswidth="23" name="text_body" placeholder="Post"></textarea>
          <input  style="width:150px; margin-bottom:5px; color:#D6A200" class="ui black button" type="submit" value="Post"> 
        </form>

    </div>


    <div id="feedHolder">
      <div id="feed" style="float:left"  >
        <div v-for="item in jsonData.items1" v-bind:key="item.postID">
          <div class="item">
            <div class="header-container">
              <div class="header">
                <div class="avatar"> <img src="../assets/profile.jpg" alt="Smiley face" height="33" width="33"> </div>
                <div class="username"> {{ item.poster.firstName + " " + item.poster.lastName }}  </div>
              </div>
            </div>
            <div v-if="item.imageOrText">
              <img src="../assets/picture.jpg" alt="Smiley face" width="600">
            </div>
            <div v-else>
              <h5>{{item.text}}</h5>
            </div>
            <div class="content">
              <div class="comments" style="padding-left: 20px;">
                <div v-for="comment in item.comments" v-bind:key="comment.postID">
                  <div class="name">{{ comment.poster.firstName + " " + comment.poster.lastName }}</div>
                  <div class="comment">{{ comment.text }}</div>
                  <br>
                </div>
              </div>
            </div>
            <div class="ui fluid input">
              <form>
                <input type="text" style="width: 300px;" placeholder="Comment">
                <button type="submit" class="ui button">Post</button>
              </form>
            </div>
          </div>
        </div>
      </div>

      <div id="feed2" style="float:right">
        <div v-for="item in jsonData.items2" v-bind:key="item.postID">
          <div class="item">
            <div class="header-container">
              <div class="header">
                <div class="avatar"> <img src="../assets/profile.jpg" alt="Smiley face" height="33" width="33"> </div>
                <div class="username"> {{ item.poster.firstName + " " + item.poster.lastName }}  </div>
              </div>
            </div>
            <div v-if="item.imageOrText">
              <img src="../assets/picture.jpg" alt="Smiley face" width="600">
            </div>
            <div v-else>
              <h5>{{item.text}}</h5>
            </div>
            <div class="content">
              <div class="comments" style="padding-left: 20px;">
                <div v-for="comment in item.comments" v-bind:key="comment.postID">
                  <div class="name">{{ comment.poster.firstName + " " + comment.poster.lastName }}</div>
                  <div class="comment">{{ comment.text }}</div>
                  <br>
                </div>
              </div>
            </div>
            <div class="ui fluid input">
              <form v-on:submit="postComment($event, item.postID)">
                <input type="text" style="width: 300px;" placeholder="Comment" v-model="comment.message" v-bind="item.postID">
                <button type="submit" class="ui button">Post</button>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name : "Feed",
  data() {
    return{
      search : "",
      comment: {
        email : "",
        message: "",
        postid : ""
      },
      postTextJSON: {
        message: "",
        email : ""
      },
      jsonData : {}
    }
  },



  beforeCreate: function(){
    if(!this.$session.exists()){
      this.$router.push('/')
      return;
    }
    const path = this.ip + '/getFeed';
    var jsonObj = {};
    this.$http.get(path)
    .then(response => {
      console.log(response);
      jsonObj = response;
    })
    .catch(error => {
      console.log("you're a failure");
    });
    this.jsonData = jsonObj;
  },
  mounted: function(){
    var canvas = this.$refs.imageCanvas
    canvas.width = 0
    canvas.height = 0
    console.log(canvas.width)
  },


  postComment : function(event, postTheId) {
    event.preventDefault();

    const path = this.ip + '/postComment';
    this.comment.email = this.$session.get('email');
    this.comment.postid = postTheId;
    
    this.$http.post(path, this.comment)
    .then(response => {
      console.log(response);
    })
    .catch(error => {
      console.log("comment failed");
    });
  },


  postImage: function(event) {
    event.preventDefault();

    var canvas = this.$refs.imageCanvas
    var thing = {
      photo: canvas.toDataURL()
    }

    const path = this.ip + '/postImage'
    
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
      if(retVal.result.length == 0) {
        console.log("yeet");
      } else {
        console.log("you are a failure");
      }
    })
    .catch(error => {
      console.log("Yeah nope")
      console.log(error)
    })

  },


  postText : function(event) {
    event.preventDefault();
    this.postTextJSON.email = this.$session.get('email');

    var path = this.ip + "/postText";

    this.$http.post(path, postTextJSON)
    .then(response => {
      console.log(response);
      this.$router.push('/feed')
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
  }

}
</script>


<style>

body {
    background: #f2f2f2;
}
#bigDad {
    text-align:center;
}
#feedHolder {
    width:1230px; 
    display: inline-block;
}
#makePost{
    display:inline-block;
    width: 300px;
    resize: horizontal;
}
#feed {
    position: relative;
    width: 600px;
    margin: 0 auto;
    display: table;
}
#feed .item {
    position: relative;
    width: 100%;
    margin: 10px auto;
    border-radius: 3px;
    background: #fff;
    overflow: hidden;
    box-shadow: 0 0 0 1px #e6e6e6;
}
#feed .item .header-container {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    clip: rect(0, auto, auto, 0);
}
#feed .item .header {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 50px;
    background: #fff;
    z-index: 1;
    box-shadow: 0 1px #e6e6e6;
}
#feed .item .header .avatar {
    cursor: pointer;
    float: left;
    width: 30px;
    height: 30px;
    margin: 10px;
    border-radius: 99em;
    background: #32a0f5;
}
#feed .item .header .username {
    text-align: left;
    cursor: pointer;
    float: left;
    width: 100px;
    height: 10px;
    margin: 16px 20px 20px 0;
    border-radius: 3px;
}

#feed .item .photo {
    position: relative;
    width: 400px;
    height: 400px;
    margin: 50px auto 0;
    background: #dceffd;
}
#feed .item .content {
    position: relative;
    padding: 10px;
    display: table;
}
#feed .item .content .caption {
    width:550px;
    height: auto;
    border-radius: 3px;
    position: relative;
    display:block;
    word-break: break-all; 
    word-wrap: break-word; 
    white-space: normal;
    text-align: left;
    padding-left:50px;
}
#feed .item .content .likes {
    cursor: pointer;
    width: 50px;
    height: 10px;
    margin-bottom: 10px;
    border-radius: 3px;
    background: #32a0f5;
}
#feed .item .content .comments li {
    margin: 5px 0 0;
    display: block;
    padding-left:10px;
}
#feed .item .content .comments li .name {
    width: 75px;
    height: auto;
    max-width: 100%;
    position: relative;
    display: block;
    text-align: left;
    left:0;
    padding-left:5px;
    font-weight:bold;
}
#feed .item .content .comments li .comment {
    width:550px;
    height: auto;
    border-radius: 3px;
    position: relative;
    display:block;
    word-break: break-all; 
    word-wrap: break-word; 
    white-space: normal;
    text-align: left;
    padding-left:5px;
    right:0;
}


#feed2 {
    position: relative;
    width: 600px;
    margin: 0 auto;
    display: table;
}
#feed2 .item {
    position: relative;
    width: 100%;
    margin: 10px auto;
    border-radius: 3px;
    background: #fff;
    overflow: hidden;
    box-shadow: 0 0 0 1px #e6e6e6;
}
#feed .item .header-container {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    clip: rect(0, auto, auto, 0);
}
#feed2 .item .header {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 50px;
    background: #fff;
    z-index: 1;
    box-shadow: 0 1px #e6e6e6;
}
#feed2 .item .header .avatar {
    cursor: pointer;
    float: left;
    width: 30px;
    height: 30px;
    margin: 10px;
    border-radius: 99em;
    background: #32a0f5;
}
#feed2 .item .header .username {
    text-align: left;
    cursor: pointer;
    float: left;
    width: 100px;
    height: 10px;
    margin: 16px 20px 20px 0;
    border-radius: 3px;
}

#feed2 .item .photo {
    position: relative;
    width: 400px;
    height: 400px;
    margin: 50px auto 0;
    background: #dceffd;
}
#feed2 .item .content {
    position: relative;
    padding: 10px;
    display: table;
}
#feed2 .item .content .caption {
    width:550px;
    height: auto;
    border-radius: 3px;
    position: relative;
    display:block;
    word-break: break-all; 
    word-wrap: break-word; 
    white-space: normal;
    text-align: left;
    padding-left:50px;
}
#feed2 .item .content .likes {
    cursor: pointer;
    width: 50px;
    height: 10px;
    margin-bottom: 10px;
    border-radius: 3px;
    background: #32a0f5;
}
#feed2 .item .content .comments li {
    margin: 5px 0 0;
    display: block;
    padding-left:10px;
}
#feed2 .item .content .comments li .name {
    width: 75px;
    height: auto;
    max-width: 100%;
    position: relative;
    display: block;
    text-align: left;
    left:0;
    padding-left:5px;
    font-weight:bold;
}
#feed2 .item .content .comments li .comment {
    width:550px;
    height: auto;
    border-radius: 3px;
    position: relative;
    display:block;
    word-break: break-all; 
    word-wrap: break-word; 
    white-space: normal;
    text-align: left;
    padding-left:5px;
    right:0;
}

</style>