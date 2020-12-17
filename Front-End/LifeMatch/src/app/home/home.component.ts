import { Component, OnInit } from '@angular/core';
import { faLinkedin} from '@fortawesome/free-brands-svg-icons';
import { faGithub} from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  faGithub = faGithub
  faLinkedin = faLinkedin

  constructor() { }

  ngOnInit(): void {
  }

}
