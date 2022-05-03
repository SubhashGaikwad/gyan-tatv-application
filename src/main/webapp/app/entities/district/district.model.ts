import dayjs from 'dayjs/esm';
import { IState } from 'app/entities/state/state.model';
import { IClientDetails } from 'app/entities/client-details/client-details.model';

export interface IDistrict {
  id?: number;
  name?: string;
  deleted?: boolean | null;
  lgdCode?: number | null;
  lastModified?: dayjs.Dayjs;
  lastModifiedBy?: string;
  state?: IState | null;
  clientDetails?: IClientDetails | null;
}

export class District implements IDistrict {
  constructor(
    public id?: number,
    public name?: string,
    public deleted?: boolean | null,
    public lgdCode?: number | null,
    public lastModified?: dayjs.Dayjs,
    public lastModifiedBy?: string,
    public state?: IState | null,
    public clientDetails?: IClientDetails | null
  ) {
    this.deleted = this.deleted ?? false;
  }
}

export function getDistrictIdentifier(district: IDistrict): number | undefined {
  return district.id;
}
