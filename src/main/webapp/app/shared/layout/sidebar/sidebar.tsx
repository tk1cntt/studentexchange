import React from 'react';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';

export interface ISidebarProps {
  isAuthenticated: boolean;
  activeMenu: string;
  activeSubMenu: string;
}

export default class Sidebar extends React.Component<ISidebarProps> {
  render() {
    const { isAuthenticated, activeMenu, activeSubMenu } = this.props;
    // if (isAuthenticated !== true) return (<div />);
    return (
      <nav className="navbar-default navbar-static-side" role="navigation">
        <div className="sidebar-collapse">
          <ul className="nav metismenu" id="side-menu">
            <li className="nav-header">
              <div className="dropdown profile-element">
                {' '}
                <span>
                  <img alt="image" className="img-circle" src="content/img/profile_small.jpg" />
                </span>
                <a data-toggle="dropdown" className="dropdown-toggle" href="#">
                  <span className="clear">
                    {' '}
                    <span className="block m-t-xs">
                      {' '}
                      <strong className="font-bold">David Williams</strong>
                    </span>{' '}
                    <span className="text-muted text-xs block">
                      Manager <b className="caret" />
                    </span>{' '}
                  </span>{' '}
                </a>
                <ul className="dropdown-menu animated fadeInRight m-t-xs">
                  <li>
                    <a href="profile.html">Profile</a>
                  </li>
                  <li>
                    <a href="contacts.html">Contacts</a>
                  </li>
                  <li>
                    <a href="mailbox.html">Mailbox</a>
                  </li>
                  <li className="divider" />
                  <li>
                    <Link to={'/logout'}>Logout</Link>
                  </li>
                </ul>
              </div>
              <div className="logo-element">IN+</div>
            </li>
            <li className={`${activeMenu === 'dashboard' ? 'active' : ''}`}>
              <Link to={'/'}>
                <i className="fa fa-th-large" /> <span className="nav-label">Dashboard</span>
              </Link>
            </li>
            <li className={`${activeMenu === 'user-management' ? 'active' : ''}`}>
              <Link to={'/account/settings'}>
                <i className="fa fa-cog" /> <span className="nav-label">User management</span> <span className="fa arrow" />
              </Link>
              <ul className="nav nav-second-level">
                <li className={`${activeSubMenu === 'setting' ? 'active' : ''}`}>
                  <Link to={'/account/settings'}>
                    <FontAwesomeIcon icon="wrench" fixedWidth /> <Translate contentKey="global.menu.account.settings">Profile</Translate>
                  </Link>
                </li>
                <li className={`${activeSubMenu === 'change-password' ? 'active' : ''}`}>
                  <Link to={'/account/password'}>
                    <FontAwesomeIcon icon="clock" fixedWidth />{' '}
                    <Translate contentKey="global.menu.account.password">Change Password</Translate>
                  </Link>
                </li>
              </ul>
            </li>
            <li>
              <a href="layouts.html">
                <i className="fa fa-th-large" /> <span className="nav-label">Layouts</span>
              </a>
            </li>
          </ul>
        </div>
      </nav>
    );
  }
}
