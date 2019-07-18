import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { Modal } from 'antd';

import Header from 'app/shared/layout/header/header';
import Sidebar from 'app/shared/layout/sidebar/sidebar';
import Footer from 'app/shared/layout/footer/footer';

import { IRootState } from 'app/shared/reducers';
import { getEntities, deleteEntity as deleteUserShippingAddress } from './user-shipping-address.reducer';

import { IUserShippingAddress } from 'app/shared/model/user-shipping-address.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserShippingAddressProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ICheckoutState {
  showDelete: boolean;
  userShippingAddressId: number;
}

export class UserShippingAddress extends React.Component<IUserShippingAddressProps> {
  state: ICheckoutState = {
    showDelete: false,
    userShippingAddressId: -1
  };
  componentDidMount() {
    this.props.getEntities();
  }

  showDeleteConfirm = userShippingAddressId => {
    this.setState({
      showDelete: true,
      userShippingAddressId
    });
  };

  handleDeleteOk = id => {
    this.props.deleteUserShippingAddress(id);
    this.setState({
      showDelete: false
    });
  };

  handleDeleteCancel = () => {
    this.setState({
      showDelete: false
    });
  };

  render() {
    const { userShippingAddressList, match } = this.props;
    return (
      <>
        <Sidebar activeMenu="shopping-cart" activeSubMenu="" />
        <div id="page-wrapper" className="gray-bg dashbard-1">
          <Header />
          <div className="row  border-bottom white-bg dashboard-header">
            Noi dung phia tren
            <button className="btn btn-primary btn-block m-t checkout-cart">
              <Link to={'/checkout'}>
                <i className="fa fa-shopping-cart" /> Đặt tất cả hàng
              </Link>
            </button>
          </div>
          <div className="row wrapper wrapper-content">
            <Table responsive>
              <thead>
                <tr>
                  <th>
                    <Translate contentKey="global.field.id">ID</Translate>
                  </th>
                  <th>
                    <Translate contentKey="studentexchangeApp.userShippingAddress.name">Người nhận</Translate>
                  </th>
                  <th>
                    <Translate contentKey="studentexchangeApp.userShippingAddress.address">Địa chỉ</Translate>
                  </th>
                  <th>
                    <Translate contentKey="studentexchangeApp.userShippingAddress.mobile">Điện thoại</Translate>
                  </th>
                  <th>
                    <Translate contentKey="studentexchangeApp.userShippingAddress.note">Ghi chú</Translate>
                  </th>
                  <th>
                    <Translate contentKey="studentexchangeApp.userShippingAddress.city">Thành phố</Translate>
                  </th>
                  <th>
                    <Translate contentKey="studentexchangeApp.userShippingAddress.district">Quận/Huyện</Translate>
                  </th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {userShippingAddressList.map((userShippingAddress, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${userShippingAddress.id}`} color="link" size="sm">
                        {userShippingAddress.id}
                      </Button>
                    </td>
                    <td>{userShippingAddress.name}</td>
                    <td>{userShippingAddress.address}</td>
                    <td>{userShippingAddress.mobile}</td>
                    <td>{userShippingAddress.note}</td>
                    <td>
                      {userShippingAddress.cityName ? (
                        <Link to={`city/${userShippingAddress.cityId}`}>{userShippingAddress.cityName}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td>
                      {userShippingAddress.districtName ? (
                        <Link to={`district/${userShippingAddress.districtId}`}>{userShippingAddress.districtName}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${userShippingAddress.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.edit">Edit</Translate>
                          </span>
                        </Button>
                        <Button onClick={this.showDeleteConfirm.bind(this, userShippingAddress.id)} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                            <Translate contentKey="entity.action.delete">Delete</Translate>
                          </span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
            {this.state.showDelete ? (
              <Modal
                title="Bạn có muốn xoá địa chỉ này?"
                visible={this.state.showDelete}
                okText="Xóa"
                okType="danger"
                cancelText="Hủy"
                onOk={this.handleDeleteOk.bind(this, this.state.userShippingAddressId)}
                onCancel={this.handleDeleteCancel}
              >
                <p>Hãy xác nhận lại thông tin trước khi thực hiện hành động xoá</p>
              </Modal>
            ) : (
              ''
            )}
          </div>
          <Footer />
        </div>
      </>
    );
  }
}

const mapStateToProps = ({ userShippingAddress, authentication }: IRootState) => ({
  userShippingAddressList: userShippingAddress.entities,
  isAuthenticated: authentication.isAuthenticated
});

const mapDispatchToProps = {
  getEntities,
  deleteUserShippingAddress
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(UserShippingAddress);
