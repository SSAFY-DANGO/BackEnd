

function InfoBox({boxName, content}) {


    return (
        <div className="border-2 rounded-xl w-[70vh] mb-5">
        <div>{boxName}</div>
        <div>{content}</div>
        </div>
    );
  }
  
  export default InfoBox;