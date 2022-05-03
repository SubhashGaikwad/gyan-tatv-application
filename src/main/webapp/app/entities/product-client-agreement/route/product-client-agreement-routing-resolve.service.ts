import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IProductClientAgreement, ProductClientAgreement } from '../product-client-agreement.model';
import { ProductClientAgreementService } from '../service/product-client-agreement.service';

@Injectable({ providedIn: 'root' })
export class ProductClientAgreementRoutingResolveService implements Resolve<IProductClientAgreement> {
  constructor(protected service: ProductClientAgreementService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductClientAgreement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((productClientAgreement: HttpResponse<ProductClientAgreement>) => {
          if (productClientAgreement.body) {
            return of(productClientAgreement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductClientAgreement());
  }
}
