import{r as o,_ as z,R as n,b as i,c as G,P as e,q as J,f as K}from"./index-CdxhiGQw.js";import{u as Q,s as U,t as W}from"./DefaultLayout-Brx2QDus.js";import{g as X,e as Y}from"./getRTLPlacement-BUCPMlgn.js";var T=o.forwardRef(function(t,C){var L=t.children,p=t.animation,S=p===void 0?!0:p,M=t.className,H=t.container,O=t.content,d=t.delay,c=d===void 0?0:d,m=t.fallbackPlacements,q=m===void 0?["top","right","bottom","left"]:m,v=t.offset,x=v===void 0?[0,8]:v,y=t.onHide,b=t.onShow,h=t.placement,F=h===void 0?"top":h,_=t.title,g=t.trigger,s=g===void 0?"click":g,f=t.visible,j=z(t,["children","animation","className","container","content","delay","fallbackPlacements","offset","onHide","onShow","placement","title","trigger","visible"]),r=o.useRef(null),l=o.useRef(null),A=Q(C,r),P=o.useRef("popover".concat(Math.floor(Math.random()*1e6))),w=U(),B=w.initPopper,R=w.destroyPopper,k=o.useState(!1),D=k[0],E=k[1],N=o.useState(f),u=N[0],a=N[1],I=typeof c=="number"?{show:c,hide:c}:c,V={modifiers:[{name:"arrow",options:{element:".popover-arrow"}},{name:"flip",options:{fallbackPlacements:q}},{name:"offset",options:{offset:x}}],placement:X(F,l.current)};return o.useEffect(function(){a(f)},[f]),o.useEffect(function(){return u&&(E(!0),r.current&&(r.current.classList.remove("fade","show"),R()),setTimeout(function(){l.current&&r.current&&(S&&r.current.classList.add("fade"),B(l.current,r.current,V),r.current.style.removeProperty("display"),r.current.classList.add("show"),b&&b())},I.show)),function(){r.current&&(r.current.classList.remove("show"),y&&y(),Y(function(){r.current&&(r.current.style.display="none"),R(),E(!1)},r.current))}},[u]),n.createElement(n.Fragment,null,n.cloneElement(L,i(i(i(i(i({},u&&{"aria-describedby":P.current}),{ref:l}),(s==="click"||s.includes("click"))&&{onClick:function(){return a(!u)}}),(s==="focus"||s.includes("focus"))&&{onFocus:function(){return a(!0)},onBlur:function(){return a(!1)}}),(s==="hover"||s.includes("hover"))&&{onMouseEnter:function(){return a(!0)},onMouseLeave:function(){return a(!1)}})),n.createElement(W,{container:H,portal:!0},D&&n.createElement("div",i({className:G("popover","bs-popover-auto",M),id:P.current,ref:A,role:"tooltip",style:{display:"none"}},j),n.createElement("div",{className:"popover-arrow"}),n.createElement("div",{className:"popover-header"},_),n.createElement("div",{className:"popover-body"},O))))});T.propTypes={animation:e.bool,children:e.node,className:e.string,container:e.any,content:e.oneOfType([e.string,e.node]),delay:e.oneOfType([e.number,e.shape({show:e.number.isRequired,hide:e.number.isRequired})]),fallbackPlacements:J,offset:e.any,onHide:e.func,onShow:e.func,placement:e.oneOf(["auto","top","right","bottom","left"]),title:e.oneOfType([e.string,e.node]),trigger:K,visible:e.bool};T.displayName="CPopover";export{T as C};