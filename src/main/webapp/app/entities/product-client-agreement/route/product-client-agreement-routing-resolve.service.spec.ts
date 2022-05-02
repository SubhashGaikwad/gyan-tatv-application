import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IProductClientAgreement, ProductClientAgreement } from '../product-client-agreement.model';
import { ProductClientAgreementService } from '../service/product-client-agreement.service';

import { ProductClientAgreementRoutingResolveService } from './product-client-agreement-routing-resolve.service';

describe('ProductClientAgreement routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: ProductClientAgreementRoutingResolveService;
  let service: ProductClientAgreementService;
  let resultProductClientAgreement: IProductClientAgreement | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(ProductClientAgreementRoutingResolveService);
    service = TestBed.inject(ProductClientAgreementService);
    resultProductClientAgreement = undefined;
  });

  describe('resolve', () => {
    it('should return IProductClientAgreement returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultProductClientAgreement = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultProductClientAgreement).toEqual({ id: 123 });
    });

    it('should return new IProductClientAgreement if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultProductClientAgreement = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultProductClientAgreement).toEqual(new ProductClientAgreement());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as ProductClientAgreement })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultProductClientAgreement = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultProductClientAgreement).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
