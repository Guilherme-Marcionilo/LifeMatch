import { AuthService } from './../service/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { environment } from './../../environments/environment.prod';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(
    private router: Router,
    public auth: AuthService

  ) { }

  ngOnInit(){
  }

  sair() {
    this.router.navigate(['/login'])
    environment.token = ''
  }

  postagens(){
    this.router.navigate(['/form-postagens'])
  }
}
