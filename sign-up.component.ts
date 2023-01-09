import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { SongsService } from '../songs.service';
import { UserAuthenticationService } from '../user-authentication.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private authService: UserAuthenticationService, private router: Router, private songService: SongsService,) { }
  //injecting user-auth services

  ngOnInit(): void {
  }

  registerForm = new FormGroup({
    "email": new FormControl(''),
    "password": new FormControl('')
  });

  
  // registering user by calling register method in user authservice, | posting email and password | id self generated, role hardcoded
  register() {
    this.authService.register(this.registerForm.value).subscribe(
      //subscribing the observable
      response => {
        console.log(response);
        alert("Registered Successfully");
        // let obj = {"emailId": this.registerForm.getRawValue().email "songName": []}
        // this.spotifyForm.patchValue({emailId:this.registerForm.getRawValue().email});
        // this.spotifyForm.patchValue({songList:Array})
        // alert("method called")
        this.songService.registerSameUserInSpotify({"emailId":this.registerForm.getRawValue().email,"songList":[]}).subscribe(
          response => {
            console.log("response : "+response);
          }
        )
        if (this.authService.isUserRegistered == true) {
          this.router.navigateByUrl("/login")
        }
      }
    );
  }

}
