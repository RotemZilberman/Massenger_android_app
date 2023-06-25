import '../../../styles/Sidebar.css';
import '../../../styles/index.css';

export default function SidebarContact({profilePic}) {
  
    return (
        <div className='sidebar-header'>
          <img src={profilePic} className='profileImage' />
          <span className='group-icons'>
            {/*camera icon*/}
            <button className='btn-Chats'>
              <i className='bi bi-three-dots-vertical icons' />
            </button>
            {/* chat icon*/}
            <button className='btn-Chats'>
              <i className='bi bi-chat-text-fill icons' />
            </button>
            {/* pepole group icon*/}
            <button className='btn-Chats'>
              <i className='bi bi-people-fill icons' />
            </button>
          </span>
        </div>
    );
}