import { Moment } from 'moment';

export interface IWard {
  id?: number;
  name?: string;
  type?: string;
  latitude?: number;
  longitude?: number;
  enabled?: boolean;
  createAt?: Moment;
  updateAt?: Moment;
  districtId?: number;
}

export const defaultValue: Readonly<IWard> = {
  enabled: false
};
