import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { Subscription } from 'rxjs';
import { LoginComponent } from './components/login/login.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, LoginComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit, OnDestroy {

  usarLogout!: boolean;
  private subscription!: Subscription;

  constructor(
    //private modalService: NgbModal,
    private router: Router,
    //private loadingServiceService: LoadingService
  ) {}

  ngOnInit() {
    const vm = this;
    vm.router.events.subscribe((event: any) => {
      vm.usarLogout = (vm.router.url !== '/loguin'); 
    });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
