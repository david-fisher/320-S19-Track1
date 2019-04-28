<template>
    <div id="bigDad">
        <div class="ui inverted segment">
            <div class="ui inverted secondary menu">
                <div class="ui icon input">
                    <input type="text" placeholder="Search members...">
                    <i class="search icon"></i>
                </div>
                <img class="item right" src="../assets/dark_logo.png" alt="Smiley face" height="60" width="60">
                <a class="item right">
                    <i class="user circle outline icon"></i>
                </a>
                <a class="active item">
                    <i class="align justify icon"></i>
                </a>
            </div>
        </div>
        <div class="makePost">
            <form class="ui form"><textarea cols="7" rows="5" charswidth="23" name="text_body" placeholder="Post"></textarea></form>
        </div>
        <div id="feedHolder">
            <div id="feed" style="float:left"  >
                <!--<% (1..10).each do %> -->
                <div class="item">
                    <div class="header-container">
                    <div class="header">
                        <div class="avatar"> <img src="../assets/profile.jpg" alt="Smiley face" height="33" width="33"> </div>
                        <div class="username"> Dhruv </div>
                    </div>
                    </div>
                    <!--<div class="photo"> -->
                        <img src="../assets/picture.jpg" alt="Smiley face" width="600">
                    <!-- </div> -->
                    
                    <div class="content">
                    <ul class="comments" style="padding-left: 20px;">
                    <!-- <% (1..3).each do %> -->
                        <li>
                            <div class="name"></div>
                            <div class="comment"></div>
                        </li>
                        <!-- <% end %> -->
                    </ul>
                    </div>
                </div>
                <div class="item">
                    <div class="header-container">
                    <div class="header">
                        <div class="avatar"> <img src="../assets/profile.jpg" alt="Smiley face" height="33" width="33"> </div>
                        <div class="username"> Dhruv </div>
                    </div>
                    </div>
                    <!--<div class="photo"> -->
                        <img src="../assets/picture.jpg" alt="Smiley face" width="600">
                    <!-- </div> -->
                    
                    <div class="content">
                    <ul class="comments" style="padding-left: 20px;">
                    <!-- <% (1..3).each do %> -->
                        <li>
                            <div class="name"></div>
                            <div class="comment"></div>
                        </li>
                        <!-- <% end %> -->
                    </ul>
                    </div>
                </div>
            </div>
                <!-- <% end %> -->
            <div id="feed" style="float:right">
            <!--<% (1..10).each do %> -->
                <div class="item">
                    <div class="header-container">
                        <div class="header">
                            <div class="avatar"> <img src="../assets/profile.jpg" alt="Smiley face" height="33" width="33"> </div>
                            <div class="username"> Dhruv </div>
                        </div>
                    </div>
                    
                    <img src="../assets/logo.png" alt="Smiley face" width="600">
                   
                    <div class="content">
                        <ul class="comments" style="padding-left:10px;">
                        <!-- <% (1..3).each do %> -->
                            <li style="padding-left:0px">
                                <div class="name" > dkhurana1999 </div>
                                <div class="comment">adasdadsada</div>
                            </li>
                            <!-- <% end %> -->
                        </ul>
                    </div>
                    <div class="ui fluid input">
                        <input type="text" style="width: 300px;" placeholder="Comment">
                        <div class="ui button">Post</div>
                    </div>
                </div>
            <!-- <% end %> -->
            </div>
        </div>
    </div>
    

</template>
<script>



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

</style>