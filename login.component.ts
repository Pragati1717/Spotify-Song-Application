import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthenticationService } from '../user-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService : UserAuthenticationService, private router : Router) { }
  //injecting user-auth services

  ngOnInit(): void {
  }

  loginForm = new FormGroup({
    "email":new FormControl(''),
    "password":new FormControl('')
  });

  //get all the data on subscribing into response data
  responseData:any;

  login(){
    this.authService.login(this.loginForm.value).subscribe(
      response=>{
        console.log(response);//checking the getting response
        this.responseData=response;//initialising responseData
        console.log("token : "+this.responseData.token);//checking if we are getting token
        //storing token in browser storage
        localStorage.setItem('jwtkey',this.responseData.token);//using key and value pair
        localStorage.setItem('userEmail',this.responseData.userEmail);//stroing user id that just registed
        alert("Welcome user "+this.responseData.userEmail);
        if(this.authService.isUserLogedIn==true){
          this.router.navigateByUrl("/songs")
        }
      }
    );
  }

}
