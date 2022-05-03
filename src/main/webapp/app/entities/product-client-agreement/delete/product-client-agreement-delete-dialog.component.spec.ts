jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ProductClientAgreementService } from '../service/product-client-agreement.service';

import { ProductClientAgreementDeleteDialogComponent } from './product-client-agreement-delete-dialog.component';

describe('ProductClientAgreement Management Delete Component', () => {
  let comp: ProductClientAgreementDeleteDialogComponent;
  let fixture: ComponentFixture<ProductClientAgreementDeleteDialogComponent>;
  let service: ProductClientAgreementService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ProductClientAgreementDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(ProductClientAgreementDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ProductClientAgreementDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(ProductClientAgreementService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
