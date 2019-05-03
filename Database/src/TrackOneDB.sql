
CREATE DATABASE IF NOT EXISTS `TrackOneDB`;

CREATE TABLE IF NOT EXISTS `TrackOneDB`.`User` (
  `userID` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `loggedIn` TINYINT(1) NOT NULL,
  `address` VARCHAR(80) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(2) NULL,
  `zip` VARCHAR(10) NULL,
  `ccNum` VARCHAR(45) NULL,
  `ccv` VARCHAR(5) NULL,
  `ccExpMon` VARCHAR(2) NULL,
  `ccExpYr` VARCHAR(4) NULL,
  `stripeID` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `profilePic` VARCHAR(45) NULL,
  `blurb` VARCHAR(512),
  `birthday` VARCHAR(45) NULL,
  `points` INT NULL,
  `inviter` VARCHAR(45) NULL,
  `hasInvited` TINYINT(1) NULL,
  `Usercol` VARCHAR(45) NULL,
  `validAccount` TINYINT(1) NULL,
  `private` TINYINT(1) NULL,
  PRIMARY KEY (`userID`, `email`),
  UNIQUE INDEX `userID_UNIQUE` (`userID` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `TrackOneDB`.`Post` (
  `postID` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `time` DATETIME NOT NULL,
  `userID` VARCHAR(45) NOT NULL,
  `text` VARCHAR(512) NOT NULL,
  `explicit` TINYINT(1) NULL,
  `visible` TINYINT(1) NULL,
  `photo` VARCHAR(45) NULL,
  `parentID` INT NULL,
  PRIMARY KEY (`postID`),
  UNIQUE INDEX `postID_UNIQUE` (`postID` ASC) VISIBLE);
  
CREATE TABLE IF NOT EXISTS `TrackOneDB`.`URL` (
  `URLID` INT NOT NULL AUTO_INCREMENT,
  `original` VARCHAR(200) NOT NULL,
  `shortened` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`URLID`, `shortened`),
  UNIQUE INDEX `URLID_UNIQUE` (`URLID` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `TrackOneDB`.`Invite` (
  `inviteID` INT NOT NULL AUTO_INCREMENT,
  `inviter` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`inviteID`),
  UNIQUE INDEX `inviteID_UNIQUE` (`inviteID` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `TrackOneDB`.`Follow` (
  `followID` INT NOT NULL AUTO_INCREMENT,
  `userID` VARCHAR(45) NOT NULL,
  `follow` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`followID`));

CREATE TABLE IF NOT EXISTS `TrackOneDB`.`Comment` (
  `commentID` INT NOT NULL AUTO_INCREMENT,
  `parentID` INT NOT NULL,
  `childID` INT NOT NULL,
  PRIMARY KEY (`commentID`));

CREATE TABLE IF NOT EXISTS `TrackOneDB`.`Photo` (
  `photoID` INT NOT NULL AUTO_INCREMENT,
  `original` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`photoID`, `original`),
  UNIQUE INDEX `photoID_UNIQUE` (`photoID` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS `TrackOneDB`.`FilterPhoto` (
  `fpID` INT NOT NULL AUTO_INCREMENT,
  `photoID` INT NOT NULL,
  `filterID` INT NOT NULL,
  `xPos` INT NOT NULL,
  `yPos` INT NOT NULL,
  `visibleToUser` TINYINT(1) NOT NULL,
  PRIMARY KEY (`fpID`));
