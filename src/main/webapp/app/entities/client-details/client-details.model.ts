import { IDistrict } from 'app/entities/district/district.model';
import { IProductClientAgreement } from 'app/entities/product-client-agreement/product-client-agreement.model';
import { ClientType } from 'app/entities/enumerations/client-type.model';
import { RegistrationLevel } from 'app/entities/enumerations/registration-level.model';

export interface IClientDetails {
  id?: number;
  customerID?: string | null;
  clientName?: string;
  clientType?: ClientType | null;
  mobileNo?: string | null;
  email?: string | null;
  pincode?: number | null;
  billingAddress?: string | null;
  companyName?: string | null;
  companyContactNo?: string | null;
  website?: string | null;
  gstinNumber?: string | null;
  companyPan?: string | null;
  pancardImageContentType?: string | null;
  pancardImage?: string | null;
  companyTan?: string | null;
  tanImageContentType?: string | null;
  tanImage?: string | null;
  gstCertificateImageContentType?: string | null;
  gstCertificateImage?: string | null;
  cancelledChequeImageContentType?: string | null;
  cancelledChequeImage?: string | null;
  udyogAadharImageContentType?: string | null;
  udyogAadharImage?: string | null;
  description?: string | null;
  nameOfBeneficiary?: string | null;
  accountNumber?: string | null;
  bankName?: string | null;
  accountType?: string | null;
  ifscCode?: string | null;
  registrationCategory?: string | null;
  registrationLevel?: RegistrationLevel | null;
  isApproved?: boolean | null;
  isActivated?: boolean | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  lastModified?: string | null;
  lastModifiedBy?: string | null;
  district?: IDistrict | null;
  productClientAgreements?: IProductClientAgreement[] | null;
}

export class ClientDetails implements IClientDetails {
  constructor(
    public id?: number,
    public customerID?: string | null,
    public clientName?: string,
    public clientType?: ClientType | null,
    public mobileNo?: string | null,
    public email?: string | null,
    public pincode?: number | null,
    public billingAddress?: string | null,
    public companyName?: string | null,
    public companyContactNo?: string | null,
    public website?: string | null,
    public gstinNumber?: string | null,
    public companyPan?: string | null,
    public pancardImageContentType?: string | null,
    public pancardImage?: string | null,
    public companyTan?: string | null,
    public tanImageContentType?: string | null,
    public tanImage?: string | null,
    public gstCertificateImageContentType?: string | null,
    public gstCertificateImage?: string | null,
    public cancelledChequeImageContentType?: string | null,
    public cancelledChequeImage?: string | null,
    public udyogAadharImageContentType?: string | null,
    public udyogAadharImage?: string | null,
    public description?: string | null,
    public nameOfBeneficiary?: string | null,
    public accountNumber?: string | null,
    public bankName?: string | null,
    public accountType?: string | null,
    public ifscCode?: string | null,
    public registrationCategory?: string | null,
    public registrationLevel?: RegistrationLevel | null,
    public isApproved?: boolean | null,
    public isActivated?: boolean | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public lastModified?: string | null,
    public lastModifiedBy?: string | null,
    public district?: IDistrict | null,
    public productClientAgreements?: IProductClientAgreement[] | null
  ) {
    this.isApproved = this.isApproved ?? false;
    this.isActivated = this.isActivated ?? false;
  }
}

export function getClientDetailsIdentifier(clientDetails: IClientDetails): number | undefined {
  return clientDetails.id;
}
