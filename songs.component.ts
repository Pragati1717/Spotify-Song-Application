import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SongsService } from '../songs.service';
import { UserAuthenticationService } from '../user-authentication.service';
import { songs } from './songsClass';

@Component({
  selector: 'app-songs',
  templateUrl: './songs.component.html',
  styleUrls: ['./songs.component.css']
})
export class SongsComponent implements OnInit {

  constructor(private songService : SongsService, private authService : UserAuthenticationService, private router:Router) { }

  ngOnInit(): void {
    this.getAllSongs();
    this.fetchPlayList();
  }

  userEmail=localStorage.getItem('userEmail');

  songArray:Array<songs>=[];

  getAllSongs(){
    this.songService.getAllSongs().subscribe(v => {
        this.songArray.length=0;
        for(let i of v){
          this.songArray.push(i);
        }
      }
    );
  }

  addToPlaylist(id:any){
    // alert(localStorage.getItem('userEmail'));
    // alert(localStorage.getItem('jwtkey'));
    alert(this.songArray[id-1].songName +  ": song is being added to playlist");
    this.songService.addSongIntoUserPlaylist({"songId":this.songArray[id-1].songId,"songName":this.songArray[id-1].songName
    ,"artist":this.songArray[id-1].artist,"songGenre":this.songArray[id-1].songGenre,"yearReleased":this.songArray[id-1].yearReleased},
    localStorage.getItem('userEmail')).subscribe(
      response =>
      console.log(response)
    );
    this.fetchPlayList();
  }

  playList:Array<songs>=[];

  fetchPlayList(){
    this.songService.fetchPlayList(localStorage.getItem('userEmail')).subscribe(v=> {
      this.playList.length=0;
        for(let i of v){
        this.playList.push(i)
        }
        console.log("array data"+this.playList);
      }
    );
  }

  removeFromPlaylist(songId:any){
    alert(this.playList[songId-1].songName+" : is being deleted from your playlist")
    this.songService.removeSongFromPlaylist(songId,localStorage.getItem('userEmail')).subscribe(
      response=>
      console.log(response)
    );
    this.fetchPlayList();
  }

  playsong(songName: any) {
    alert(  songName  +  " Playing From playlist !!!!!!! ");
  }

  logOut(){
    alert("confirm to log out")
    this.authService.isUserLogedIn==false;
    localStorage.removeItem('jwtkey');
    localStorage.removeItem('userEmail');
    this.router.navigateByUrl("");
  }

}
