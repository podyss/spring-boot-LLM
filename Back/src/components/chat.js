import { Chat, Select, Radio, RadioGroup } from '@kousum/semi-ui-vue';
import { createTextVNode as _createTextVNode, createVNode as _createVNode, defineComponent, Fragment as _Fragment, ref } from 'vue';
import { stream_chat_model } from '../api/api'
import global from '../components/global'

// https://semi-ui-vue.netlify.app/
const roleInfo = {
    user: {
        name: 'User',
        avatar:
            'https://lf3-static.bytednsdoc.com/obj/eden-cn/ptlz_zlp/ljhwZthlaukjlkulzlp/docs-icon.png'
    },
    'llama3.2': {
        name: 'llama3.2',
        avatar:
            'https://lf3-static.bytednsdoc.com/obj/eden-cn/ptlz_zlp/ljhwZthlaukjlkulzlp/other/logo.png'
    },
    'gemma2:2b': {
        name: 'gemma2:2b',
        avatar:
            'https://lf3-static.bytednsdoc.com/obj/eden-cn/ptlz_zlp/ljhwZthlaukjlkulzlp/other/logo.png'
    }
};
const commonOuterStyle = {
    border: '1px solid var(--semi-color-border)',
    borderRadius: '16px',
    margin: '8px 16px',
    height: '700px',
    width: '1000px',
};
let id = 0;
function getId() {
    return `id-${id++}`;
}
const uploadProps = {
    action: 'https://api.semi.design/upload'
};
const uploadTipProps = {
    content: '自定义上传按钮提示信息'
};
const DefaultChat = defineComponent(() => {
    const list = [{
        value: 'llama3.2',
        label: 'llama3.2',
    }, {
        value: 'gemma2:2b',
        label: 'gemma2:2b',
    }, {
        value: 'qwen2.5:7b',
        label: 'qwen2.5:7b',
    }, {
        value: 'phi3.5:3.8b',
        label: 'phi3.5:3.8b',
    }];
    var model = list[0].value;
    const onChange = value => {
        model = value;
    };
    const message = ref([]);
    const mode = ref('bubble');
    const align = ref('leftRight');
    const onMessageSend = (content, attachment) => {
        message.value = [...message.value, {
            role: model,
            status: 'loading',
            createAt: Date.now(),
            id: getId(),
            content: '',
        }];
        const textDecoder = new TextDecoder();
        stream_chat_model(model, content).then(async (reader) => {
            while (true) {
                const { done, value } = await reader.read();
                const lastMessage = message.value[message.value.length - 1];
                if (done || lastMessage.status == 'complete') {   
                    let newMessage = {
                        ...lastMessage,
                        content: `${lastMessage.content}`,
                        status: 'complete'
                    };
                    message.value = [...message.value.slice(0, -1), newMessage];
                    global.refreshKey1++
                    global.refreshKey2++
                    break;
                }
                const chunkText = textDecoder.decode(value)
                try {
                    let text = chunkText?.replace("data:", "") || ""
                    text = JSON.parse(text).result.output.content
                    let newMessage = {
                        ...lastMessage
                    };
                    newMessage = {
                        ...newMessage,
                        content: `${lastMessage.content}${text}`,
                        status: 'incomplete'
                    };
                    message.value = [...message.value.slice(0, -1), newMessage];
                } catch (err) { }
            }
        });
    };
    const onChatsChange = chats => {
        message.value = chats;
    };

    const onStopGenerator = () => {
        const lastMessage = message.value[message.value.length - 1];
        if (lastMessage.status && lastMessage.status !== 'complete') {
            const lastMessage = message.value[message.value.length - 1];
            let newMessage = {
                ...lastMessage
            };
            newMessage.status = 'complete';
            message.value = [...message.value.slice(0, -1), newMessage];
        } else {
            message.value = [...message.value];
        }
    };
    return () => _createVNode(_Fragment, null, [
        _createVNode("span", {
            "style": {
              "display": 'flex',
              "flexDirection": 'column',
              "rowGap": '8px',
              "align-items": 'center',
            }
          }, [
            _createVNode(Select, {
                "defaultValue": model,
                "style": {
                    width: '180px',
                },
                "optionList": list,
                "insetLabel": "模型",
                "onChange": onChange,
            }, null),
            _createVNode(
                Chat, {
                'key': align.value + mode.value,
                'align': align.value,
                'mode': mode.value,
                'style': commonOuterStyle,
                'chats': message.value,
                'roleConfig': roleInfo,
                'onChatsChange': onChatsChange,
                'onMessageSend': onMessageSend,
                "showStopGenerate": true,
                "onStopGenerator": onStopGenerator,
                // 'uploadProps': uploadProps,
                // 'uploadTipProps': uploadTipProps
            }, null),
          ]),
    ]);
});
export default DefaultChat;