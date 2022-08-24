using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class player : MonoBehaviour
{
    [SerializeField] private Transform groundCheckTransform =null;
    [SerializeField] private LayerMask playerMask;
    private bool jumpKey;
    private float hroizontalInput;
    private Rigidbody rigidbodyComponent;
    private int superJumps;
    


    // Start is called before the first frame update
    void Start()
    {
        rigidbodyComponent = GetComponent<Rigidbody>();    
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetKeyDown(KeyCode.Space))
        {
            jumpKey = true;
        }
        
        hroizontalInput = Input.GetAxis("Horizontal");
    }

    private void FixedUpdate()
    {
         rigidbodyComponent.velocity = new Vector3(hroizontalInput,rigidbodyComponent.velocity.y,0);    
        
        if(Physics.OverlapSphere(groundCheckTransform.position,0.1f,playerMask).Length == 0)
        {
            return;
        }
        if (jumpKey == true)
        {
            float jumpPower = 5f;
            if(superJumps > 0)
            {
                jumpPower *= 2;
                superJumps--;
            }
            rigidbodyComponent.AddForce(Vector3.up* jumpPower, ForceMode.VelocityChange);
            jumpKey = false;
        }

      
    }

    private void OnTriggerEnter(Collider other)
    {
        if(other.gameObject.layer == 9)
        {
            Destroy(other.gameObject);
            superJumps++;
        }
    }

}
