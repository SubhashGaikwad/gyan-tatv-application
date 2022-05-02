import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ProductClientAgreementComponent } from '../list/product-client-agreement.component';
import { ProductClientAgreementDetailComponent } from '../detail/product-client-agreement-detail.component';
import { ProductClientAgreementUpdateComponent } from '../update/product-client-agreement-update.component';
import { ProductClientAgreementRoutingResolveService } from './product-client-agreement-routing-resolve.service';

const productClientAgreementRoute: Routes = [
  {
    path: '',
    component: ProductClientAgreementComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProductClientAgreementDetailComponent,
    resolve: {
      productClientAgreement: ProductClientAgreementRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProductClientAgreementUpdateComponent,
    resolve: {
      productClientAgreement: ProductClientAgreementRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProductClientAgreementUpdateComponent,
    resolve: {
      productClientAgreement: ProductClientAgreementRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(productClientAgreementRoute)],
  exports: [RouterModule],
})
export class ProductClientAgreementRoutingModule {}
