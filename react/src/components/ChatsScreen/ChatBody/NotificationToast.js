import React, { useEffect } from 'react';
import "../../../styles/index.css";
import "../../../styles/Sidebar.css";

export default function NotificationToast({ toastContent }) {
  useEffect(() => {
    if (toastContent.message !== "") {
      const toastElement = document.getElementById('EpicToast');
      const toast = new window.bootstrap.Toast(toastElement, {
        animation: true,
        delay: 4000
      });
      toast.show();
    }
  }, [toastContent]);

  return (
    <div className="toast" id="EpicToast" role="alert" aria-live="assertive" aria-atomic="true" style={{ position: "absolute", bottom: "10px", left: "10px"}} data-bs-autohide="true">
      <div className="toast-header d-flex justify-content-between">
        <strong className="mr-auto">{toastContent.senderName}</strong>
        <div className = "d-flex align-items-center justify-content-center.">
          <small className="me-2">sec ago</small>
          <button type="button" className="btn-close" aria-label="Close" data-bs-dismiss="toast"/>
        </div>
      </div>
      <div className="toast-body" style={{ overflow: "hidden", textOverflow: "ellipsis", whiteSpace: "nowrap" }}>
        {toastContent.message}
      </div>
    </div>
  );
}
