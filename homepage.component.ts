import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SongsService } from '../songs.service';
import { songs } from '../songs/songsClass';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private songService : SongsService, private router :Router) { }

  ngOnInit(): void {
    this.getAllSongs();
  }

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


}
