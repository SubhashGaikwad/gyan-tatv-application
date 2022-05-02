import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IProductClientAgreement } from '../product-client-agreement.model';
import { ProductClientAgreementService } from '../service/product-client-agreement.service';

@Component({
  templateUrl: './product-client-agreement-delete-dialog.component.html',
})
export class ProductClientAgreementDeleteDialogComponent {
  productClientAgreement?: IProductClientAgreement;

  constructor(protected productClientAgreementService: ProductClientAgreementService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.productClientAgreementService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
