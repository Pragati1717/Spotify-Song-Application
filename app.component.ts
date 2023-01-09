import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { SongsService } from './songs.service';
import { songs } from './songs/songsClass';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Spotify';

  

}
