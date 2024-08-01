import { Component } from '@angular/core';
import { StorageService } from '../../../../auth/services/storage/storage.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrl: './admin-navbar.component.css'
})
export class AdminNavbarComponent {
  constructor(
    private storage: StorageService,
    private router: Router,
    private toast: ToastrService
  ) { }

  logout() {
    this.storage.logout();
    this.router.navigate(['/']);
    this.toast.success("Admin logged out successfully.", "Logged Out")
  }
}
