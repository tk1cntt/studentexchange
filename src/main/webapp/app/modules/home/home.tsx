import React from 'react';
import { connect } from 'react-redux';

import HomeUser from './home-user';
import HomeStaff from './home-staff';
import { AUTHORITIES } from 'app/config/constants';
import { hasAnyAuthority } from 'app/shared/auth/private-route';

export interface IHomeProp extends StateProps, DispatchProps {}

export class Home extends React.Component<IHomeProp> {
  render() {
    if (this.props.isUser) {
      return <HomeUser />;
    }
    return <HomeStaff />;
  }
}

const mapStateToProps = storeState => ({
  isUser: hasAnyAuthority(storeState.authentication.account.authorities, [AUTHORITIES.USER])
});

const mapDispatchToProps = {};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Home);
