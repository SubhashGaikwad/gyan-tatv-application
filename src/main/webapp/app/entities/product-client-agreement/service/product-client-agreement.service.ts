import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IProductClientAgreement, getProductClientAgreementIdentifier } from '../product-client-agreement.model';

export type EntityResponseType = HttpResponse<IProductClientAgreement>;
export type EntityArrayResponseType = HttpResponse<IProductClientAgreement[]>;

@Injectable({ providedIn: 'root' })
export class ProductClientAgreementService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/product-client-agreements');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(productClientAgreement: IProductClientAgreement): Observable<EntityResponseType> {
    return this.http.post<IProductClientAgreement>(this.resourceUrl, productClientAgreement, { observe: 'response' });
  }

  update(productClientAgreement: IProductClientAgreement): Observable<EntityResponseType> {
    return this.http.put<IProductClientAgreement>(
      `${this.resourceUrl}/${getProductClientAgreementIdentifier(productClientAgreement) as number}`,
      productClientAgreement,
      { observe: 'response' }
    );
  }

  partialUpdate(productClientAgreement: IProductClientAgreement): Observable<EntityResponseType> {
    return this.http.patch<IProductClientAgreement>(
      `${this.resourceUrl}/${getProductClientAgreementIdentifier(productClientAgreement) as number}`,
      productClientAgreement,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProductClientAgreement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProductClientAgreement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addProductClientAgreementToCollectionIfMissing(
    productClientAgreementCollection: IProductClientAgreement[],
    ...productClientAgreementsToCheck: (IProductClientAgreement | null | undefined)[]
  ): IProductClientAgreement[] {
    const productClientAgreements: IProductClientAgreement[] = productClientAgreementsToCheck.filter(isPresent);
    if (productClientAgreements.length > 0) {
      const productClientAgreementCollectionIdentifiers = productClientAgreementCollection.map(
        productClientAgreementItem => getProductClientAgreementIdentifier(productClientAgreementItem)!
      );
      const productClientAgreementsToAdd = productClientAgreements.filter(productClientAgreementItem => {
        const productClientAgreementIdentifier = getProductClientAgreementIdentifier(productClientAgreementItem);
        if (
          productClientAgreementIdentifier == null ||
          productClientAgreementCollectionIdentifiers.includes(productClientAgreementIdentifier)
        ) {
          return false;
        }
        productClientAgreementCollectionIdentifiers.push(productClientAgreementIdentifier);
        return true;
      });
      return [...productClientAgreementsToAdd, ...productClientAgreementCollection];
    }
    return productClientAgreementCollection;
  }
}
