import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { ProductClientAgreementComponent } from './list/product-client-agreement.component';
import { ProductClientAgreementDetailComponent } from './detail/product-client-agreement-detail.component';
import { ProductClientAgreementUpdateComponent } from './update/product-client-agreement-update.component';
import { ProductClientAgreementDeleteDialogComponent } from './delete/product-client-agreement-delete-dialog.component';
import { ProductClientAgreementRoutingModule } from './route/product-client-agreement-routing.module';

@NgModule({
  imports: [SharedModule, ProductClientAgreementRoutingModule],
  declarations: [
    ProductClientAgreementComponent,
    ProductClientAgreementDetailComponent,
    ProductClientAgreementUpdateComponent,
    ProductClientAgreementDeleteDialogComponent,
  ],
  entryComponents: [ProductClientAgreementDeleteDialogComponent],
})
export class ProductClientAgreementModule {}
